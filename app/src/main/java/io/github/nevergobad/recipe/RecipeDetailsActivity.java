package io.github.nevergobad.recipe;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import io.github.nevergobad.BR;
import io.github.nevergobad.R;
import io.github.nevergobad.databinding.ActivityRecipeDetailsBinding;
import io.github.nevergobad.dependencyinjection.ComponentManager;
import io.github.nevergobad.dependencyinjection.RecipeServiceModule;
import io.github.nevergobad.models.Recipe;
import io.github.nevergobad.services.RecipeService;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RecipeDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_TITLE = "title";

    @Inject
    RecipeService mRecipeService;

    private String mRecipePath;
    private String mTitle;
    private Presenter mPresenter;
    private ActivityRecipeDetailsBinding mBinding;


    public static void viewRecipe(@NonNull Context context, @NonNull Uri recipeUri, @Nullable String title) {
        Intent intent = new Intent(context, RecipeDetailsActivity.class);
        intent.setData(recipeUri);
        if (!TextUtils.isEmpty(title)) {
            intent.putExtra(EXTRA_TITLE, title);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_details);

        final Intent intent = getIntent();
        mRecipePath = intent.getData().getPath();
        final String title = intent.getStringExtra(EXTRA_TITLE);
        mTitle = TextUtils.isEmpty(title) ? getString(R.string.recipe_details_title) : title;
        setTitle(mTitle);

        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ComponentManager.get().getNetworkComponent().plus(new RecipeServiceModule()).inject(this);

        mPresenter = new Presenter();
        mBinding.setPresenter(mPresenter);
        mPresenter.loadRecipe();





    }


    //user loader
    // todo presenter state loading error, connection error , loaded finished
    // show loading spinner or retry button


    public enum State {LOADING, LOADED, FAILURE};

    public class Presenter extends BaseObservable {

        private State mState = State.LOADING;

        public void shareRecipe(View view) {
            Log.d("HELLO", "shareRecipe() called with: view = [" + view + "]");
        }

        @Bindable
        public State getState() {
            return mState;
        }

        private void setState(State state) {
            if (state != mState) {
                mState = state;
                notifyPropertyChanged(BR.state);
            }
        }

        public void loadRecipe() {
            mRecipeService.retrieveRecipe(mRecipePath).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleSubscriber<Recipe>() {
                @Override
                public void onSuccess(Recipe value) {
                    setState(State.LOADED);
                }

                @Override
                public void onError(Throwable error) {
                    setState(State.FAILURE);
                }
            });
        }


    }
}
