package api.endpoints;

/**
 * Login: http://localhost:3000/admin/login
 * Create Publisher: http://localhost:3000/admin/api/resources/Publisher/actions/new
 * Create post: http://localhost:3000/admin/api/resources/Post/actions/new
 * Edit post: http://localhost:3000/admin/api/resources/Post/records/{id}/edit
 */

public class Routes {

    public static final String HOST = "localhost:3000";
    public static final String ORIGIN = "http://localhost:3000";
    public static final String BASE_URL = "http://localhost:3000/admin/api/resources";

    // Login urls
    public static final String POST_ADMIN_LOGIN_URL ="http://localhost:3000/admin/login";

    // Publisher urls
    public static final String POST_PUBLISHER_URL = BASE_URL + "/Publisher/actions/new";

    // Posts urls
    public static final String POST_NEW_POST_URL = BASE_URL + "/Post/actions/new";
    public static final String EDIT_POST_URL = BASE_URL + "/Post/records/{id}/edit";

}
