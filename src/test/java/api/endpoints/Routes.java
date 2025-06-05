package api.endpoints;

/**
 * Login: http://localhost:3000/admin/login
 * Create Publisher: http://localhost:3000/admin/api/resources/Publisher/actions/new
 * Create post: http://localhost:3000/admin/api/resources/Post/actions/new
 * Edit post: http://localhost:3000/admin/api/resources/Post/records/{id}/edit
 * Delete post: http://localhost:3000/admin/api/resources/Post/records/{id}/delete
 */

public class Routes {

    public static final String COOKIE = "adminjs=s%3AZ-LfSKpaRJv_pN38Ui-xA1kzzT6HGxC2.OEqcWp84eDADTHDE6lp4DOGTRGlIytXmGO37f7fHiB8";

    public static final String HOST_IP = "http://172.18.0.7";
    public static final String PORT = ":3000";
    public static final String HOST = HOST_IP + PORT;
    public static final String BASE_URL = HOST + "/admin/api/resources";

    // Login urls
    public static final String POST_ADMIN_LOGIN_URL = HOST + "/admin/login";

    // Publisher urls
    public static final String POST_PUBLISHER_URL = BASE_URL + "/Publisher/actions/new";
    public static final String DELETE_PUBLISHER_URL = BASE_URL + "/Publisher/records/{id}/delete";

    //Profile urls
    public static final String DELETE_PROFILE_URL = BASE_URL + "/Profile/records/{id}/delete";

    // Posts urls
    public static final String POST_NEW_POST_URL = BASE_URL + "/Post/actions/new";
    public static final String EDIT_POST_URL = BASE_URL + "/Post/records/{id}/edit";
    public static final String DELETE_POST_URL = BASE_URL + "/Post/records/{id}/delete";

}
