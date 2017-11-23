# FortniteAPI
unofficial fortnite api

hey dudes, nobody has done this yet, so I did it.

grab stats directly from fortnite's servers.

setting it up initially is a bit tricky, fortnite takes some client info and some authentication data, 
hashes each of them with what I assume is md5, then appends them and encrypts it with base64 and sends
it in the authorization html header to their oauth endpoints.

to set this up, you need to listen to http requests from your pc and grab two of these encrypted auth
headers, one from the epic games launcher's oauth request, and one from the fortnite client's oauth
request.

I used fiddler 4 to monitor the http requests and grab the info.

Once you have these, simply pass these values along with your username/password as arguments in the
credentials constructor, which you will then use to initialize an instance of the FortniteAPI class,
which you can then grab stats with.

    <dependencies>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.0</version>
        </dependency>
        <dependency>
            <groupId>com.google.http-client</groupId>
            <artifactId>google-http-client</artifactId>
            <version>1.23.0</version>
        </dependency>
    </dependencies>

# // TODO: token refreshing
