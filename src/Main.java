import java.util.*;

public class Main {
    private static LinkedHashMap<String, ArrayList<Gallery>> fetchData(String fPath) {
        String[] input = FileController.readFile(fPath);
        LinkedHashMap<String, ArrayList<Gallery>> GalleryList = new LinkedHashMap<>();
        for (String inputLine : input) {
            String[] buffor = inputLine.split(" ");
            final String COUNTRY_KEY = buffor[0];
            final String CITYNAME = buffor[1];
            Gallery gallery = new Gallery(CITYNAME);
            ArrayList<Gallery> listOfGalleries = new ArrayList<>();
            if (GalleryList.containsKey(COUNTRY_KEY)) {
                listOfGalleries = GalleryList.get(COUNTRY_KEY);
            }

            for (int i = 2 ; i < buffor.length; i+=2) {
                int x = Integer.parseInt(buffor[i]);
                int y = Integer.parseInt(buffor[i+1]);
                if (x == 0 && y == 0) break;
                Store store = new Store(x,y);
                gallery.addStore(store);
            }

            listOfGalleries.add(gallery);
            GalleryList.put(COUNTRY_KEY, listOfGalleries);
        }
        return GalleryList;
    }

    private static String generateList(HashMap<String, ArrayList<Gallery>> list) {
        StringBuilder output = new StringBuilder();
        list.forEach((key, val) -> output.append(key).append(" ").append(val.size()).append("\n"));
        return output.toString();
    }

    private static String generateAreaList(HashMap<String, ArrayList<Gallery>> GalleryList) {
        StringBuilder output = new StringBuilder();
        GalleryList.forEach((Country, _Gallery) -> {
            _Gallery.forEach((gallery) -> {
                String CityName = gallery.getCityName();
                int area = Gallery.sumOfStoreArea(gallery.getStoreList());
                int storeNumbers = gallery.getStoreList().size();
                String _output = CityName + " " + area + " " + storeNumbers + "\n";
                output.append(_output);
            });
        });
        return output.toString();
    }

    private static String minAndMaxValues(HashMap<String, ArrayList<Gallery>> GalleryList) {
        HashMap<String, Integer> galleryMap = new HashMap<>();

        final String[] maxKey = {""};
        final String[] minKey = {""};

        GalleryList.forEach((Country, _Gallery) -> {
            _Gallery.forEach(gallery -> {
                String CityName = gallery.getCityName();
                int area = Gallery.sumOfStoreArea(gallery.getStoreList());
                galleryMap.put(CityName, area);

                if (maxKey[0].equals("")) maxKey[0] = CityName;
                if (minKey[0].equals("")) minKey[0] = CityName;

                if (galleryMap.get(maxKey[0]) < area) maxKey[0] = CityName;
                else if (galleryMap.get(minKey[0]) > area) minKey[0] = CityName;
            });
        });

        String maxString = maxKey[0] + " " + galleryMap.get(maxKey[0]) + "\n";
        String minString = minKey[0] + " " + galleryMap.get(minKey[0]) + "\n";
        return maxString + minString;
    }

    private static String minAndMaxOfSameTypeStores(HashMap<String, ArrayList<Gallery>> GalleryList) {
        final String[] maxKey = {""};
        final String[] minKey = {""};

        // Wymaga redukcji ilości forEach
        HashMap <String, HashMap<Integer, Integer>> _values = new HashMap<>();
        GalleryList.forEach((Country, _Gallery) -> {
            _Gallery.forEach(gallery -> {
                HashMap <Integer, Integer> values = new HashMap<>();
                gallery.getStoreList().forEach(store -> {
                    int area = store.calcArea();
                    int val = 1;
                    if (values.containsKey(area)) {
                        val = values.get(area) + 1;
                    }
                    values.put(area, val);
                });
                _values.put(gallery.getCityName(), values);

                if (maxKey[0].equals("")) maxKey[0] = gallery.getCityName();
                if (minKey[0].equals("")) minKey[0] = gallery.getCityName();

                if (_values.get(maxKey[0]).size() < values.size()) maxKey[0] = gallery.getCityName();
                else if (_values.get(minKey[0]).size() > values.size()) minKey[0] = gallery.getCityName();
            });
        });

        String minString = minKey[0] + " " + _values.get(minKey[0]).size() + "\n";
        String maxString = maxKey[0] + " " + _values.get(maxKey[0]).size() + "\n";

        return minString + maxString;
    }

    public static void main(String[] args) {
        LinkedHashMap<String, ArrayList<Gallery>> GalleryList = fetchData("./src/Resources/galerie.txt");
        String zad4_1 = generateList(GalleryList);
        String zad4_2a = generateAreaList(GalleryList);
        String zad4_2b = minAndMaxValues(GalleryList);
        String zad4_3 = minAndMaxOfSameTypeStores(GalleryList);

        FileController.saveToFile("wynik4_1.txt", zad4_1);
        FileController.saveToFile("wynik4_2a.txt", zad4_2a);
        FileController.saveToFile("wynik4_2b.txt", zad4_2b);
        FileController.saveToFile("wynik4_3.txt", zad4_3);
    }
}
