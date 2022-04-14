import java.util.ArrayList;

public class Gallery {
    private final String cityName;
    private final ArrayList<Store> storeList = new ArrayList<>();
    private int sameTypeStores = 0;

    public Gallery(String cityName) {
        this.cityName = cityName;
    }

    public void addStore(Store store) {
        this.storeList.add(store);
    }

    public String getCityName() {
        return this.cityName;
    }

    public ArrayList<Store> getStoreList() {
        return this.storeList;
    }

    public static int sumOfStoreArea(ArrayList<Store> list) {
        return list.stream().reduce(0, (pv, cv) -> pv + cv.calcArea(), Integer::sum);
    }


}
