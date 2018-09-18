import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public abstract class Grader {
  /*
   * You need to implement the following functions:
   */

  public abstract void addFriend(String cow1, String cow2, int timestamp);
  public abstract boolean checkFriend(String cow1, String cow2, int timestamp);
  public abstract int getNumberOfFriends(String cow, int timestamp);

  public abstract Grader newInstance();

  /*
   * Call this from main().
   */
  public void run() throws IOException {
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter w = new PrintWriter(System.out);

    StringTokenizer st = new StringTokenizer(r.readLine());
    int numCommands = Integer.parseInt(st.nextToken());

    for (int i = 0; i < numCommands; ++i) {
      st = new StringTokenizer(r.readLine());
      String commandType = st.nextToken();

      if (commandType.equals("A")) {
        String cow1 = st.nextToken(); String cow2 = st.nextToken();
        int timestamp = Integer.parseInt(st.nextToken());

        addFriend(cow1, cow2, timestamp);
      } else if (commandType.equals("C")) {
        String cow1 = st.nextToken(); String cow2 = st.nextToken();
        int timestamp = Integer.parseInt(st.nextToken());

        if (checkFriend(cow1, cow2, timestamp)) {
          System.out.println("TRUE");
        } else {
          System.out.println("FALSE");
        }
      } else if (commandType.equals("N")) {
        String cow = st.nextToken();
        int timestamp = Integer.parseInt(st.nextToken());

        System.out.println(getNumberOfFriends(cow, timestamp));
      } else {
        throw new IOException("Invalid command type: " + commandType);
      }
    }
  }
}
