public class HTTPRequestFeature {

    public static void Run(String url) {

        HttpRequest request = new HttpRequest();

        if(request.readURL(url)) {

            System.out.println(
                "=== HTTP REQUEST RESULTS ===");

            System.out.println(request);
        }
        else {
            System.out.println(
                "Unable to retrieve URL.");
        }
    }
}