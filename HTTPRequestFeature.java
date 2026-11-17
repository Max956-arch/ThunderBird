public class HTTPRequestFeature {

    public static void Run(String url) {

        // MD: creates a new HttpRequest
        HttpRequest request = new HttpRequest();

        // MD: Attempt to read data from the URL
        if(request.readURL(url)) {

            System.out.println(
                "=== HTTP REQUEST RESULTS ===");

            // MD: prints the contents of the request object
            System.out.println(request);
        }
        else {
            // MD: displays an error if the URL cannot be read
            System.out.println(
                "Unable to retrieve URL.");
        }
    }
}