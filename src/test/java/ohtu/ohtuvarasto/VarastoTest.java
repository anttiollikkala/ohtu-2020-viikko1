package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenKonstruktorinArvo() {
        Varasto v = new Varasto(-1);
        assertTrue(v.getTilavuus() == 0);
    }

    @Test
    public void konstruktoriSaldolla() {
        Varasto v = new Varasto(10, 2);
        assertTrue(v.getTilavuus() == 10);
        assertTrue(v.getSaldo() == 2);
    }

    @Test
    public void konstruktoriSaldollaNegatiivinen() {
        Varasto v = new Varasto(-1, 0);
        assertTrue(v.getTilavuus() == 0);
    }

    @Test
    public void konstruktoriSaldollaTilavuusPienempi() {
        Varasto v = new Varasto(5, 10);
        assertTrue(v.getTilavuus() == 5);
        assertTrue(v.getSaldo() == 5);
    }

    @Test
    public void konstruktoriSaldollaAlkusaldoNegatiivinen() {
        Varasto v = new Varasto(5, -1);
        assertTrue(v.getSaldo() == 0);
    }

    @Test
    public void negatiivinenLisaysEiMuutaMitaan() {
        double ennen = varasto.getSaldo();
        varasto.lisaaVarastoon(-1);
        assertTrue(ennen == varasto.getSaldo());
    }

    @Test
    public void ylimenevatHeitetaanPois() {
        double tilaa = varasto.paljonkoMahtuu();
        varasto.lisaaVarastoon(tilaa + 10);
        assertTrue(varasto.paljonkoMahtuu() == 0);
    }

    @Test
    public void eiVoiOttaaNegatiivista() {
        double ennen = varasto.getSaldo();
        varasto.otaVarastosta(-1);
        assertTrue(varasto.getSaldo() == ennen);
    }

    @Test
    public void eiVoiOttaaEnempaaKuinOn() {
        double ennen = varasto.getSaldo();
        varasto.otaVarastosta(ennen + 10);
        assertTrue(varasto.getSaldo() == 0);
    }

    @Test
    public void oikeaMerkkijono() {
        double saldo = varasto.getSaldo();
        double mahtuu = varasto.paljonkoMahtuu();
        assertTrue(varasto.toString().equals("saldo = " + saldo + ", vielä tilaa " + mahtuu));
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}