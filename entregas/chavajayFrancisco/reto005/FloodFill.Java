import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class AdvancedFloodFill {
    
    private static final boolean DEBUG_MODE = false;
    private static final char EMPTY_CHAR = '.';
    private static final char BORDER_CHAR = '#';
    
    public static void main(String[] args) {
        char[][] image = createImageFromTemplate(TEMPLATE_001);
        printImage(image);
        System.out.println("Imagen original -- Pulse enter para comenzar");
        new Scanner(System.in).nextLine();
        fillAllAreas(image);
        printImage(image);
    }

    private static String[] PLANTILLA_000 = {
        ".....................................",
        ".....................................",
        "..########################...........",
        "..#......................#...#####...",
        "..#..........########....#####...#...",
        "..#..........#......#............#...",
        "..#..........########.........####...",
        "..######......................#......",
        ".......#..#####.....###########......",
        "..######.................#...#####...",
        "..#..........########....#####...#...",
        "..#..........#......#............#...",
        "..#..........########.........####...",
        "..######......................#......",
        ".......#..#####.....###########......",
        ".......####...#######................",
        ".....................................",
        "....................................."
    };

    private static final String[] TEMPLATE_001 = {
        ".....................................................",
        ".....................................................",
        "..########........#########.......########...........",
        "..#......#........#.......#.......#......#...........",
        "..#......#........#.......#.......#......#...........",
        "..#......#........#.......#.......#......#...........",
        "..########........#########.......########...........",
        ".....................................................",
        "..########....########....########....########.......",
        "..#......#....#......#....#......#....#......#.......",
        "..#......#....#......#....#......#....#......#.......",
        "..########....########....########....########.......",
        ".....................................................",
        "..#######.........#######.........#######............",
        "..#.....#.........#.....#.........#.....#............",
        "..#.....#.........#.....#.........#.....#............",
        "..#######.........#######.........#######............",
        ".....................................................",
        ".....................................................",
        "....................................................."
    };

    private static String[] PLANTILLA_002 = {
        " ############################################+##################################################### ",
        " ++------------++---+++---++++----+##############################-+++++++++--------++--------++++++ ",
        "                              -###########+-###-----+-++++###########+                              ",
        "                           -#######+-++-+#++###+++++++++#++--+--########-                           ",
        "                         +#####+-+-+##+-+#-++--+#++####-#++-+#+++++-######+                         ",
        "                       +#####+#+-++--#+-+#####+#+++-+##++-+-##--+#++#++#####+                       ",
        "                     +#####+-+#--#-  -+++---####+++++##+#+-+#+  -++--#+ +####+-                     ",
        "                    +#####+  ####+  --##+---###-+++++##+-+-+#+-  -++--#++######-                    ",
        "                  -+##+--##++#+-+----##+++-###+-++++-#####--##--- -+++####+--###+-                  ",
        "                  +##+   -####++ --+##-+--####- ++++-+###+++-###--  #+-##+   -+##+-                 ",
        "                 +##+     +##-+#+-###--+-##+#+- ++++--#####++-###-- +++##-     +##+-                ",
        "                +##+-     +##-+#-### +-+##-+#-  ++++--+####++#++##--++-##-      +##+                ",
        "               +##+---    +##-####+ +-#-#+-#++++#####+-##-###+#++##++++#+        +##+               ",
        "              -##+-+##--  -##-###+---#-######+##############-#+#++##++#+       ---+##-              ",
        "              +##--+++#++--#+##-- ++#-##+++#+####+-+#####+-## #+#--#####-----++++-++#+              ",
        "              ##--+-#+#++-+###+- -++###++##################### #+#++++###----+###+--##+             ",
        "             -##--+-#+#++###+   +#+#+#+++##-############+#+--##-#+#-   +##+++-+##+-+-##-            ",
        "             +#+---+#-#-##+-   -#+#+##- ##--            -## +-##+#+++   +##++++####++##-            ",
        "             ##---####-##+    +#+####---##-             -##+ -##++#+#+   -###-+####--##+            ",
        "             ##-----#+##--   +#+#-###+ +#+-   -+#+-     --##-- ##++#++-   -###+###+- +##            ",
        "            -##- --+-##+--  +#+##+##- -##-    +###+      -+#+ --##++#+++  --#++-#++- -##            ",
        "            +## --#####+-- +#+#++##-- +#+-    ##+##       -##  --## +#++- --+#++#+--  ##            ",
        "            ##+-+#####+-  -++#+-##--  ##-     #####       -##-  -### +#+++  -+##### - ##            ",
        "            ##-###+      +++#+ ##+-  +##      #+###       --##  --##+ +#+++     -###--##            ",
        "           -#####-      -++#+ +##-  -##-      +++--         ##+  +-##  +#++-     -+##+##-           ",
        "           +###+       +++#+ -##--  +#+-      ---           -##   -##+ -+#+++      -####+           ",
        "           ###+       -++#+ -##+-   ##-                     -##-  --##+  +#++-      -+###           ",
        "           ###-------++-#+  ###----+##-----------------------+#+-----##+  +#-++-------###           ",
        " #+        ##########################+++++++++++++++++++++++++###########################           ",
        " -+        +########################+++++++++++++++++++++++++++#########################+           ",
        " #+                                                                                                 ",
        "                                                                                                    ",
        "                                                                                                    "
    };

    private static char[][] createImageFromTemplate(String[] template) {
        char[][] image = new char[template.length][];
        for (int i = 0; i < template.length; i++) {
            image[i] = template[i].toCharArray();
        }
        return image;
    }

    private static void fillAllAreas(char[][] image) {
        floodFillIterative(image, 0, 0, '\'', EMPTY_CHAR);
        char[] fillChars = {'o', 'x', '|'};
        int fillIndex = 0;
        
        for (int y = 0; y < image.length; y++) {
            for (int x = 0; x < image[y].length; x++) {
                if (image[y][x] == BORDER_CHAR) {
                    floodFillIterative(image, x, y, fillChars[fillIndex++ % fillChars.length], BORDER_CHAR);
                }
            }
        }
    }

    private static void floodFillIterative(char[][] image, int startX, int startY, char newChar, char targetChar) {
        if (image[startY][startX] == newChar) return;
        
        Deque<Point> stack = new ArrayDeque<>();
        stack.push(new Point(startX, startY));
        
        while (!stack.isEmpty()) {
            Point current = stack.pop();
            int x = current.x;
            int y = current.y;
            
            if (y < 0 || y >= image.length || x < 0 || x >= image[y].length || 
                image[y][x] != targetChar || image[y][x] == newChar) {
                continue;
            }
            
            image[y][x] = newChar;
            
            if (DEBUG_MODE) {
                printImage(image);
                new Scanner(System.in).nextLine();
            }
            
            stack.push(new Point(x + 1, y));
            stack.push(new Point(x - 1, y));
            stack.push(new Point(x, y + 1));
            stack.push(new Point(x, y - 1));
        }
    }

    private static class Point {
        final int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int countEnclosedAreas(char[][] image) {
        char[][] copy = deepCopyImage(image);
        int count = 0;
        
        for (int y = 0; y < copy.length; y++) {
            for (int x = 0; x < copy[y].length; x++) {
                if (copy[y][x] == EMPTY_CHAR) {
                    count++;
                    floodFillIterative(copy, x, y, 'o', EMPTY_CHAR);
                }
            }
        }
        return count;
    }

    private static char[][] deepCopyImage(char[][] image) {
        char[][] copy = new char[image.length][];
        for (int i = 0; i < image.length; i++) {
            copy[i] = image[i].clone();
        }
        return copy;
    }

    public static void printImage(char[][] image) {
        clearConsole();
        for (char[] row : image) {
            System.out.println(row);
        }
        System.out.println();
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
            }
            System.out.flush();
        } catch (Exception e) {
            System.out.println("\n".repeat(50));
        }
    }
}