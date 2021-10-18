package social.net.service.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.streaming.responses.GetServerUrlResponse;
import com.vk.api.sdk.queries.groups.GroupsGetQuery;
import com.vk.api.sdk.queries.users.UsersGetQuery;
import com.vk.api.sdk.streaming.clients.VkStreamingApiClient;
import com.vk.api.sdk.streaming.clients.actors.StreamingActor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class VkInitializer {
    @Value("${access.token}")
    private String accessToken;
    TransportClient transportClient = new HttpTransportClient();

    VkApiClient vk = new VkApiClient(transportClient);
    VkStreamingApiClient streamingClient = new VkStreamingApiClient(transportClient);

    //Create service actor
    Integer appId = -175711177;
    UserActor actor = new UserActor(appId, accessToken);

    public void getActor() {
        UsersGetQuery usersGetQuery = vk.users().get(actor);
        UsersGetQuery fields = usersGetQuery.fields();
        System.out.println(fields);
    }

}
/*access_token=44a9cbf4ad7f51d2473c44418669dce16e806d557b6de0827f0b093bcf3e47c94b5d3e4ada17da93db3a8&expires_in=0
&user_id=254530506*/


/*https://oauth.vk.com/blank.html#access_token=44a9cbf4ad7f51d2473c44418669dce16e806d557b6de0827f0b093bcf3e47c94b5d3e4ada17da93db3a8
&expires_in=0
&user_id=254530506*/
