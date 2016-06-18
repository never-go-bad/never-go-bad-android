package io.github.nevergobad.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;

import static io.github.nevergobad.services.UserAgentInterceptor.USER_AGENT_HEADER;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by andre on 18/06/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserAgentInterceptorTest {

    private static final String USER_AGENT = "Biro Biro";

    private @Mock Interceptor.Chain mChain;
    private @Captor ArgumentCaptor<Request> mRequestCaptor;

    @Test
    public void interceptShouldSetHeader() throws IOException {
        UserAgentInterceptor interceptor = new UserAgentInterceptor(USER_AGENT);
        when(mChain.request()).thenReturn(new Request.Builder().url("http://www.google.com").get().build());
        interceptor.intercept(mChain);
        verify(mChain).proceed(mRequestCaptor.capture());
        Request request = mRequestCaptor.getValue();
        List<String> userAgentHeader = request.headers(USER_AGENT_HEADER);
        assertThat(userAgentHeader, hasSize(1));
        assertThat(userAgentHeader, contains(USER_AGENT));
    }

}
