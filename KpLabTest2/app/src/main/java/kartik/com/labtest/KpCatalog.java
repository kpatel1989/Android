package kartik.com.labtest;

import java.util.ArrayList;

/**
 * Created by macadmin on 2016-12-08.
 */

public class KpCatalog {
    private ArrayList<KpCd> kpItems;

    public KpCatalog(){
        kpItems = new ArrayList<>();
    }

    public int addItem(KpCd item) {
        kpItems.add(item);
        return kpItems.size();
    }

    public KpCd getItem(int index){
        return kpItems.get(index);
    }

    public ArrayList<KpCd> getAllItems() {
        return kpItems;
    }

}

