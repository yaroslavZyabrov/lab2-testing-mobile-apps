import com.techyourchance.testdoublesfundamentals.exercise4.FetchUserProfileUseCaseSync;
import com.techyourchance.testdoublesfundamentals.example4.networking.NetworkErrorException;
import com.techyourchance.testdoublesfundamentals.exercise4.networking.UserProfileHttpEndpointSync;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FetchUserProfileUseCaseSyncTest {

    private UserProfileHttpEndpointSync mUserProfileHttpEndpointSync;
    private FetchUserProfileUseCaseSync SUT;

    @Before
    public void setup() {
        mUserProfileHttpEndpointSync = mock(UserProfileHttpEndpointSync.class);
        SUT = new FetchUserProfileUseCaseSync(mUserProfileHttpEndpointSync);
    }

    @Test
    public void fetchUserProfileSync_success_userIdPassedToEndpoint() throws NetworkErrorException {
        String userId = "user123";
        SUT.fetchUserProfileSync(userId);
        verify(mUserProfileHttpEndpointSync).getUserProfile(userId);
    }
}
