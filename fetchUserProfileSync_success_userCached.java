import com.techyourchance.testdoublesfundamentals.exercise4.FetchUserProfileUseCaseSync;
import com.techyourchance.testdoublesfundamentals.exercise4.FetchUserProfileUseCaseSync.UseCaseResult;
import com.techyourchance.testdoublesfundamentals.example4.networking.NetworkErrorException;
import com.techyourchance.testdoublesfundamentals.exercise4.networking.UserProfileHttpEndpointSync;
import com.techyourchance.testdoublesfundamentals.exercise4.users.User;
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
    public void fetchUserProfileSync_success_userCached() throws NetworkErrorException {
        String userId = "user123";
        User user = new User(userId, "John Doe", "image_url");
        when(mUserProfileHttpEndpointSync.getUserProfile(userId))
                .thenReturn(new UserProfileHttpEndpointSync.EndpointResult(
                        UserProfileHttpEndpointSync.EndpointResultStatus.SUCCESS, userId, user.getFullName(), user.getImageUrl()
                ));

        UseCaseResult result = SUT.fetchUserProfileSync(userId);

        verify(mUsersCache).cacheUser(user);
        assertEquals(UseCaseResult.SUCCESS, result);
    }
}
