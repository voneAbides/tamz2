package com.example.netactivity;

public class Entry {
    public final String kod;
    public final String mena;
    public final String mnozstvi;
    public final String kurz;
    public final String zeme;



    // TODO 3. Rozsirit dalsi udaje ve tride, ktere se budou vest pro kazdou menu
    // TODO 3. To zahrnuje i upraveni konstruktoru
            
    Entry(String kod, String mena, String mnozstvi, String kurz, String zeme) {

        this.kod = kod;
        this.mena = mena;
        this.mnozstvi = mnozstvi;
        this.kurz = kurz;
        this.zeme = zeme;
    }
}
