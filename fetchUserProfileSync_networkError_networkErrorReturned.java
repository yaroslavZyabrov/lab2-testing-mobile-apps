import com.techyourchance.testdoublesfundamentals.exercise4.FetchUserProfileUseCaseSync;
import com.techyourchance.testdoublesfundamentals.exercise4.FetchUserProfileUseCaseSync.UseCaseResult;
import com.techyourchance.testdoublesfundamentals.example4.networking.NetworkErrorException;
import com.techyourchance.testdoublesfundamentals.exercise4.networking.UserProfileHttpEndpointSync;
import com.techyourchance.testdoublesfundamentals.exercise4.users.UsersCache;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

public class FetchUserProfileUseCaseSyncTest {

    private UserProfileHttpEndpointSync mUserProfileHttpEndpointSync;
    private UsersCache mUsersCache;
    private FetchUserProfileUseCaseSync SUT;

    @Before
    public void setup() {
        mUserProfileHttpEndpointSync = mock(UserProfileHttpEndpointSync.class);
        mUsersCache = mock(UsersCache.class);
        SUT = new FetchUserProfileUseCaseSync(mUserProfileHttpEndpointSync, mUsersCache);
    }

    @Test
    public void fetchUserProfileSync_networkError_networkErrorReturned() throws NetworkErrorException {
        String userId = "user123";
        when(mUserProfileHttpEndpointSync.getUserProfile(userId))
                .thenThrow(new NetworkErrorException());

        UseCaseResult result = SUT.fetchUserProfileSync(userId);

        assertEquals(UseCaseResult.NETWORK_ERROR, result);
    }
}
