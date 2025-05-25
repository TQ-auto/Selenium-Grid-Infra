package api.endpoints;

/**
 * Login: http://localhost:3000/admin/login
 * Create Publisher: http://localhost:3000/admin/api/resources/Publisher/actions/new
 * Create post: http://localhost:3000/admin/api/resources/Post/actions/new
 * Edit post: http://localhost:3000/admin/api/resources/Post/records/{id}/edit
 * Delete post: http://localhost:3000/admin/api/resources/Post/records/{id}/delete
 */

public class Routes {

    public static final String COOKIE = "adminjs=s%3A2lBhnUD-Z8qfdKrI3Z4vhIcxE-y_gPVu.AsbQXMVpWjIpHqk4QKBsbvFzlgbfomeEYy8JXSUZ1SY";

    public static final String HOST = "localhost:3000";
    public static final String ORIGIN = "http://localhost:3000";
    public static final String BASE_URL = "http://localhost:3000/admin/api/resources";

    // Login urls
    public static final String POST_ADMIN_LOGIN_URL ="http://localhost:3000/admin/login";

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
