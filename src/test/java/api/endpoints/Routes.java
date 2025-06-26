package api.endpoints;

import java.io.IOException;

import static generalutils.TestUtils.getPropertyValueFromPropertiesFile;

/**
 * Login: http://localhost:3000/admin/login
 * Create Publisher: http://localhost:3000/admin/api/resources/Publisher/actions/new
 * Create post: http://localhost:3000/admin/api/resources/Post/actions/new
 * Edit post: http://localhost:3000/admin/api/resources/Post/records/{id}/edit
 * Delete post: http://localhost:3000/admin/api/resources/Post/records/{id}/delete
 */

public class Routes {

    public static final String COOKIE;

    static {
        try {
            COOKIE = getPropertyValueFromPropertiesFile("request_cookie");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String HOST_IP;

    static {
        try {
            if(getPropertyValueFromPropertiesFile("run_locally").equals("false"))
                HOST_IP = "http://" + getPropertyValueFromPropertiesFile("admin_app_ip");
            else
                HOST_IP = "http://localhost";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String PORT = ":3000";
    public static final String HOST = HOST_IP + PORT;
    public static final String BASE_URL = HOST + "/admin/api/resources";

    // Login urls
    public static final String POST_ADMIN_LOGIN_URL = HOST + "/admin/login";

    // Publisher urls
    public static final String POST_PUBLISHER_URL = BASE_URL + "/Publisher/actions/new";
    public static final String GET_PUBLISHER_URL = BASE_URL + "/Publisher/actions/{id}/show";
    public static final String EDIT_PUBLISHER_URL = BASE_URL + "/Publisher/actions/{id}/edit";
    public static final String DELETE_PUBLISHER_URL = BASE_URL + "/Publisher/records/{id}/delete";

    //Profile urls
    public static final String DELETE_PROFILE_URL = BASE_URL + "/Profile/records/{id}/delete";

    // Posts urls
    public static final String POST_NEW_POST_URL = BASE_URL + "/Post/actions/new";
    public static final String EDIT_POST_URL = BASE_URL + "/Post/records/{id}/edit";
    public static final String DELETE_POST_URL = BASE_URL + "/Post/records/{id}/delete";

}
