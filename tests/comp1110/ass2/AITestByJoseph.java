package comp1110.ass2;

import org.junit.Test;

import static comp1110.ass2.AI.alphabeta;
import static comp1110.ass2.AI.validTiles;
import static comp1110.ass2.Scoring.getScore;
import static comp1110.ass2.StratoGame.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Joseph Meltzer on 13/09/2016.
 */
public class AITestByJoseph {
    @Test
    /* Test that every generated move is not empty. */
    public void testNonEmpty() {
        for (char i = 'K'; i<='T'; i++) {
            assertFalse("Placement MMUA with piece " + i + " gave an empty string", generateMove("MMUA", i, 'A') == "");
             for (String placement : greenTestPlacements) {
                if (isPlacementWellFormed(placement+"AA"+i+"A")) {
                    assertFalse("Placement " + placement + " with piece " + i + " gave an empty string", generateMove(placement, i, 'A') == "");
                }
            }
        }
        for (char i = 'A'; i<='J'; i++) {
            for (String placement : redTestPlacements) {
                if (isPlacementWellFormed(placement+"AA"+i+"K")) {
                    assertFalse("Placement " + placement + " with piece " + i + " gave an empty string", generateMove(placement, i, 'A') == "");
                }
            }
        }
    }

    @Test
    /* Test that every generated move is valid on its placement */
    public void testValid() {
        for (char i = 'K'; i<='T'; i++) {
            assertTrue("Placement MMUA with piece " + i + " generated an invalid piece", isPlacementValid("MMUA"+generateMove("MMUA", i, 'A')));
            for (String placement : greenTestPlacements) {
                if (isPlacementWellFormed(placement+"AA"+i+"A")) {
                    assertTrue("Placement " + placement + " with piece " + i + " generated an invalid piece", isPlacementValid(placement+generateMove(placement, i, 'A')));
                }
            }
        }
        for (char i = 'A'; i<='J'; i++) {
            for (String placement : redTestPlacements) {
                if (isPlacementWellFormed(placement+"AA"+i+"K")) {
                    assertTrue("Placement " + placement + " with piece " + i + " generated an invalid piece", isPlacementValid(placement+generateMove(placement, i, 'A')));
                }
            }
        }
    }

    @Test
    /* Test that the Alpha-Beta generator with depth set to 1 returns the same result as the old naive generator */
    public void testAlphaBetaAtDepth1() {
        for (char i = 'K'; i<='T'; i++) {
            for (String placement : greenTestPlacements){
                if (isPlacementWellFormed(placement+"AA"+i+"A")) {
                    String ab = alphabeta(placement, i, 'A', 1, 0, -100, 1000, true, true).move;
                    String og = oldGenerator(placement, i, 'A');
                    assertTrue("With placement "+placement+", AB gave "+ab+", old gave "+og, ab.equals(og));
                }
            }
        }
        for (char i = 'A'; i<='J'; i++) {
            for (String placement : redTestPlacements){
                if (isPlacementWellFormed(placement+"AA"+i+"A")) {
                    String ab = alphabeta(placement, i, 'A', 1, 0, -100, 1000, true, false).move;
                    String og = oldGenerator(placement, i, 'A');
                    assertTrue("With placement "+placement+", AB gave "+ab+", old gave "+og, ab.equals(og));
                }
            }
        }
    }

    static char[] checkOrder = {'M','L','N','K','O','J','P','I','Q','H','R','G','S','F','T','E','U','D','V','C','W','B','X','A','Y','Z'};

    /* Generator that searches only immediately available moves */
    public static String oldGenerator(String placement, char piece, char oppPiece) {
        String bestMove = "";
        int bestScore = -100;
        for (String move : validTiles(placement)) {
            char x = move.charAt(0);
            char y = move.charAt(1);
            for (char o = 'A'; o <= 'D'; o++) {
                if (piece >= 'A' && piece <= 'J') {
                    if (isPlacementValid(placement + x + y + piece + o) && getScoreForPlacement(placement + x + y + piece + o, false)  - getScoreForPlacement(placement + x + y + piece + o, true)> bestScore) {
                        bestMove = "" + x + y + piece + o;
                        bestScore = getScoreForPlacement(placement + x + y + piece + o, false) - getScoreForPlacement(placement + x + y + piece + o, true);
                    }
                }
                if (piece >= 'K' && piece <= 'T') {
                    if (isPlacementValid(placement + x + y + piece + o) && getScoreForPlacement(placement + x + y + piece + o, true) - getScoreForPlacement(placement + x + y + piece + o, false) > bestScore) {
                        bestMove = "" + x + y + piece + o;
                        bestScore = getScoreForPlacement(placement + x + y + piece + o, true) - getScoreForPlacement(placement + x + y + piece + o, false);
                    }
                }
            }
        }
        return bestMove;
    }

    /* moveScore nested class, required in the use of the AB and probabilistic generators */
        public static class moveScore {
        public String move;
        private int score;

        moveScore(String move, int score) {
            this.move = move;
            this.score = score;
        }
    }

    @Test
    /* Test that the Probabilistic generator with 0 levels of probabilistic search is equivalent to the previous Alpha-Beta generator.
    *  Takes around 30 seconds to complete */
    public void testProbIsAB() {
        for (char i = 'K'; i<='T'; i++) {
            for (String placement : greenTestPlacements){
                if (isPlacementWellFormed(placement+"AA"+i+"A")) {
                    String pb = alphabeta(placement, i, 'A', 2, 0, -100, 1000, true, true).move;
                    String og = alphabeta2(placement, i, 'A', 2, -100, 1000, true, true).move;
                    assertTrue("With placement "+placement+", Prob gave "+pb+", AB gave "+og, pb.equals(og));
                }
            }
        }
        for (char i = 'A'; i<='J'; i++) {
            for (String placement : redTestPlacements){
                if (isPlacementWellFormed(placement+"AA"+i+"A")) {
                    String pb = alphabeta(placement, i, 'A', 2, 0, -100, 1000, true, false).move;
                    String og = alphabeta2(placement, i, 'A', 2, -100, 1000, true, false).move;
                    assertTrue("With placement "+placement+", Prob gave "+pb+", AB gave "+og, pb.equals(og));
                }
            }
        }
    }

    /* Alpha-beta generator
       Copied from AI class, and modified to use same move ordering.
     */
    public static moveScore alphabeta2(String placement, char piece, char opiece, int depth, int a, int b, boolean maximising, boolean initialGreen) {
        if (depth==0) return new moveScore("", getScore(placement, initialGreen)-getScore(placement, !initialGreen));
        if (maximising) {
            int bestScore = -100;
            String bestMove = "c";
            for (String move : validTiles(placement)) {
                char x = move.charAt(0);
                char y = move.charAt(1);
                for (char o='A'; o<='D'; o++) {
                    if (isPlacementValid(placement+x+y+piece+o)) {
                        moveScore ab = new moveScore("" + x + y + piece + o, alphabeta2(placement + x + y + piece + o, opiece, piece, depth - 1, a, b, false, initialGreen).score);
                        if (ab.score > bestScore) {
                            bestScore = ab.score;
                            bestMove = ab.move;
                        }
                        a = Math.max(a, bestScore);
                        if (b <= a) break;
                    }
                }

            }
            return new moveScore(bestMove, bestScore);
        }
        else {
            int bestScore = 1000;
            String bestMove = "";
            for (String move : validTiles(placement)) {
                char x = move.charAt(0);
                char y = move.charAt(1);
                for (char o='A'; o<='D'; o++) {
                    if (isPlacementValid(placement+x+y+piece+o)) {
                        moveScore ab = new moveScore("" + x + y + piece + o, alphabeta2(placement + x + y + piece + o, opiece, piece, depth - 1, a, b, true, initialGreen).score);
                        if (ab.score < bestScore) {
                            bestScore = ab.score;
                            bestMove = ab.move;
                        }
                        b = Math.min(b, bestScore);
                        if (b <= a) break;
                    }
                }
            }
            return new moveScore(bestMove, bestScore);
        }
    }

    /* Test placements, from TestUtility. */
    static final String greenTestPlacements[] = {
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAKJLAPPABQKMCJNECRLRBLQGBNPKBLPHDPRPBJOFAMRRDOKHCMINCOTGAQITDTIIBRPKCKIDCRSOBTPCCSRQASGCAQKPBQUADPRLCQNJAIPSBGOIB",
            "MMUAMLODKNJDLPTDKOBBJNOCJMBAOLPALJEDJPNBQLFAHNNCLKHBLRLCNKHDHKKBKSGANKQAMJDBOJQDQOACKVPCPNACOPMDJOIAPLLBHJDCJLMDGIFCFOTDJXCCRPSCHVGAGQRDJVCBSNKBEIECLLRCIZID",
            "MMUANMTDOOADNPRAPLBCNOQCPPDAPNRDQLCDSNPBPPJDPITAMPBBPRLBMQIDTMNBRSICQTLBULFBSKPDROCBRIMDPUHBPHQDPSJDMSNDPLDBMMOALLHDRHKBKPGBNIKAJLFAVLSDQUACWMOBTMEAVOMDSREA",
            "MMUAKMPDKKCDKOKDKQIDMKPAKRGAJNOCNNADHMQDIPJCGQNDKMHAKHKAGMECMHQAJIABOLMDKTEAKWRDMSBCJWSCGOBDILNDPNDDOGLCFOFCNITAKQGALFMARLJBKYRDMYIANROANQDDSMLDGWFDHRTAQRHB",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAOJNBMPGAQNQAINCAIQRDMJABNQOAJJDDJRQASOEBJTRAPPCBPRSAKPIBHILAUPICRMMDLPADUNTDRPFBURLCKIHDPUKDJMJCJQKDVRGDMHPCWPHC",
            "MMUAKMKAMKIAIMNDJKICJNQBNMEDIIQBGJDCFILCEJDBKNOCIGABIMMAHLGCKIOADKFBHONDJGHAEMMBEICALGKAMHAAEGPBCHCACLPBBMHBNOLCBPJDFLSCKFBCCNRAKFEBOIRDMOGDEETBBEFAEOSBEKBB",
            "MMUANMLDNNGAMOTBNQFBKMLDLLJDOLRDOLDBJMPCORADQNSBRLICJKQDPOHBHJKAKQEAGISDJRJCPNOAKPFCNSKBITACHPNDFIECROOAQQBBOSTAGQBAOJMCKTDDGMRDFICDPIPDSJICFLMCTHGAFQQCUIHA",
            "MMUAMKNAMPCCONMBNJDCNQSBLQFBPOPAOREANJRBQNDDPKNCOTBBORKBKKGAKMSAKRCBPTPDQUGBOOKDKPICMTTCQRIAOLRBSUJCLUMBKIAAROQARLJCRJLDIJBDOPQBKWHCHRTDTUHDMHOCGPABJNLBJYFD",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAMPSAMLDANKKCQPJCMSQCMJHCRLRCNSEDKPMBTLACNITDKRABTJPCQRCCSILCLHBAKUQDJIGDMFNAPHIBUJPDUHFCSIKBOFHDOKRDXJCBWILCQJGA",
            "MMUAKNQDLLCBJNNCLMJDHMTDNLJCONQCJKHBFLLDEJGAJJMDPLGAMOTALKHDDJOBGIBCAJRAJPBCKHSBDJCAMRNCRKABNOPCHKECLQSCMKFAGLPCNRFALQKBPKDCGPOASMIBJNMAOOAAGRRBKSEDOOLDSPIC",
            "MMUAKNMAIOIDMOKAMLFDJQKDIPBBOMMBPMDAMNLBLJHALPRBMQJAQOPCMLDAHMOAPQECSMOCORGAGMTCTNHCPONDPLCDNJTAKLJCGNPBVMEBPQSBVPGCLMLCRPFDQQQAGQAAQLNBTOABFSSCMIICKMQDLGBD",
            "MMUANLQAPLEAMKTDMOCAPJSBLQCDLLMDKOJBHOMDOPHBRKOBORBBHPKBMSFCPHNANIADQILAPNBBQGPDSHHAJKTAREJCJNODSFIBTEPASKGDNFRALUACHORATDICUHLATLEBNEQDQPDDPKNDIQDDRRKDRCFD",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAMKSDNJDDROQCMQGDMIKCNHBDJPPAJRIALGLBMOICPQLDPREAQHTCIPACOFOAOTFCRMRCPJJAMUQAGPHBEPMCGPHAMPKBSNGAKVPAPTAALSRDSQCC",
            "MMUANMMDOLFDLMRBLKDAQLTDPJHANNSARMIASJMCOPBBMQKCJMIBQMQBUMCCVNNBKLHCQPPDIOJDGONDIPGBUNODTHBCWLKBJPJAPQSDKJGDLOTBVQADUPPBQIFCFOLBQIDBTRLAOKEAFQOAKKADSNRACOEA",
            "MMUAMOOBMOACKMTDNNGDOOMBORJCNKNAJNHDOSPBOIHBKOSBJQFANSLDKMCCJSKAMSABPMSBMQIAHOPAQLIBHTRAKUGDLVMBPHDCFOKAPTEAHMTAOUBBMJNAIRECPRQDSRCCPKRDOJJBKWOBNHBCMWLARPFA",
            "MMUAKMRDKOICIMKDNOACOPTDPRJDMPKBPSFALLPDHOGDJQNCOQABMNQDSRDCKKLCKHFBQOMCTSHBHKOALJHDKGLDNICATUQAQUEBPVNBPSBDOOOAPXEBUVMASTCBUXPAGODBMHSBVZBDOHSCSRIBGKRBNFJB",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAONLBNJFBIMNDPQICHMQBQOJDRNPDJNABIPPAJKEDGNQBKQGDHKLBQRHDNRRCTMCCLRKBJHHAMTSCRPAATRMCLGGAURTDUNIBOIKAFMDCLVRDVTBC",
            "MMUAMPOCNPDAPOKARNGBQMMDLRIDSPTDJPADKSRDTNFANNLANRHALRRAQMHAIRTAHRDBUONARLEDQPQAIQIDHQQCSRJCJNMDRJFDHNSBSTBCSVPCMKCASWOBILJBNMKDURACWRSCYRCBHULDUXECWNNCSIBD",
            "MMUANNMAOPEDPONDMLJCNMKDOLBDNQSCKQFAMOTBKSAAQPQAQSBCSRPCKMAAQURCTRHDUSSBPKIDITLDRVDDQINAPVJARWOBOXCDQGMAJOICRYOBIVGCTRPCSSEBSKRBNSGBMNTALUCCMWKAWRDBQMLAVQFC",
            "MMUALLKBOMICQMLCOOGDROSCSPECNOOBRLADRINBSKCDVKKCLOGBRRODSNBDQRQBUOJBMQTCPHHAPKQCUMIAQUMDOSDAMRTARVFBPGPDTVECNUSCJNJAPDRBNLCCQRMAIODBJLNCPVABRYPCRTBDVORDPCFC",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAQNPDILBASMLBQOIASKKBINABQKLCJPCDPIQATOIBQHKDQNCBPKRDROADPGNBMPHAHOMBJKJCKJSDLRHCIQTBKTDDLPQBSJGCEORDSHEDOEPAUIFB",
            "MMUALLPBKNDCJLRCIOGAKJKAKOJALITCMKBDJJNCKRACNHRCKMDALRODJGHBJKKAPHHBNMTDINGCJTSCKSJAGJMDQGECRHLCFHFDPLNARJEDQGQDGOBANQQAFIIBTGSCKVICUGPDEPCANNOALFABRKLBKECD",
            "MMUALORAMLIDOLNALRADNPLCPNJBKSODKUFDPPRAORJDNQTBMRADNPKBOSGAKLNDPJBAJLPBSPHBHKSAPIECMTTDNKFDQTQDJVCCITMAQRHDIWSASOIDLVOBLNCCUPMCHYDDQKQATKECKPPBLRGCSILAHPDD",
            "MMUAOMTBNKDALLNAMJIBKKQCMPACLNPBMHFBNOPDKHCBNLMBOJDDQKKDNPIAJJRCQIBCNNNAPIEBJGLDPOHARIMAPNGDHKODIMJCFMSDKQECTIKAKRBASLSDVLJBJHLDGNCBNSRDIPFBJDOBGKHCLGTDOOGA",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFARMKCQNCAQNQBKQCDPMKDQKHBLJOALQICPIRBOHGDQMRBKRJANRLCJSECLHSBJGIDPPQAJRFAJTPBLFBCJPMCJJGAINTBMDHCQGLCHGDDRLPBMPAA",
            "MMUAKNPAKMADLOSALQJBLKOAMSHDKOMDMPGALTQDKRFBJOLBKTIBMSLAOMICIUTCJNBCKKTCFUJAOQRBNTEAJJKCEVABIKQBFVECLJNDJVDAPQRDJOBCKXSBMMGBEUMCIYFDOUOAGVCAQMPBKOCCHXNCLGDB",
            "MMUALMSCKODDONNBOPGBIMRDLJJBHKKAHICBKPLBNJFCOIODHPFAGMOANGGDOGMBNEEDIKMBMCIAPMQCMOEARMPBPPHDIQTALQCAMETBQQHBINLAKKABSLPCKNJBNNSAKLBDPMKDGQACRSQCPHDDMGNBTLID",
            "MMUAMORANMGDNQLCOOIDKNTDOKAAJOOCPMBDMKNDKLDCLLNDKODANRTAMLCBOLKCOPCAINRCQOJDOQOCLJJCNJSDRQECTQMBLRECTTQDKTBCUUPBMTHDNJSBNVGDISKBKRFAVQMCSOADHLQDRUHDWULCNXFC",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAQMPDROFCJKKAHLJAKKNBLKHDFLQDSMDDUNPDRPGBWNLAUMBBNKTDJOEASOSDMOACFMKAGNIAHORAYNHAJJRCGMABRROBEKGBJQMBLOCDPRLCOTID",
            "MMUAMPTDOOGCOQKCMKEANLQAPREDQLPBNSFCRLPAQKADKLOATLGDQLKCNUHDTNSDPKHCPMRBMUICQSSBRSJDLKMCUSDBUQLAVOBAUJRAWLFBPONAUIIDWSNCKOCDSQODRNABJLQBSTCAKKMAXRJDOILBLWBC",
            "MMUAMPMDLPDCMQPBLMICLKQCORJCPQTAQRIAMTNDKIABLJKBMLHDMRLAJPCBJORDNNGALUSDJMJCMQTCKRFCJPSAMTBARTQDPSEBKTMCIOACPMRAPLECUTNCNVBDGPPAIRFCTUOASSHDUROCUODAEPKAQSCD",
            "MMUAMPNCJPEDKRTCKOBDNOPDOMICNQSDNSDCHRNALLGDNNLBISCAPRLBIRBAKJPBPQADGSOCPNEAIKRAHTJBPMKDIJGCRRQBSNFCGKKCMQJBKTTCROAANPMBHVHBPOMCUOHCIGOBJWFBESRBHYDDUPSAEJCB",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAOONDQLAAJPODHPIAMJQAHOFDGMKAPQECOKSDFQHAGSLDMHDBIRMBQLIBGOLBLQGBEOPCHNCCJTRDNIGDMHRAGKAAGRPBFMBCHTKBJMCBMPQAKRJB",
            "MMUAMKNBMPCCKNPDJODCOLLBHOECOKPDLRACRKMCPIEAIQODPJDCGPOAOPJBSISANRBDGNLCHNFBSLNCKJHDTMSCFLFANQKAQRJCUKTCILHBDMQDGKBCOORDVIGBTOTDOOAAJLQAJICCQORDKTIDPTKDPPIA",
            "MMUANORCONDARNTCNOADTMLCNMFDLLODNKFCQLSAQJGANPQBMQBBPIRAMQHAORQDQRJDLSNAUMDDLVOCONGDJKKDSSABIKNCJHHAQHSCLMIBJUPBRSCDWNKBJFIBJWTAQOBASJMAXOEALWMAOTEDIXPBIVCB",
            "MMUALONDMQJCLRTBLMCCKLLCNPIDKSNBJKDCKROAIRHBPPMDGQHBRQKDQQFBEPTDNQFAPRQBSRCALNRDTTBDQQMDDOABPUQDOUJBMSSAKIGBNRPADRGDDPLDOXBDUSODMPDANNSDWREBKOKCGSEBNKPAUSIC",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAMPPANJCBQPODJLHCSPPDRQEBSMRALKADNNTDSRGAMJMAPPIBGLQATTACJNKBTLFCELLAVRGCJPRACMBAMLSANQJAAMQDJIDAUPKDIJICVMNBRLHC",
            "MMUANMNAKMDAJOOCMLHCIMRDGMFDKOKAJLCDQMLCNOBBFKTAHOECJIKBIPJAFOPCMIEADKSAJHDCFQTCNRGCESNDPNAASLRBKJHAHKQDDRACMGMBSNJBKILDMPGBEUMDOPCANGSDGHIAJOPABLBDBIOABSIC",
            "MMUANNLANMBDPLLCKNEAKPOANQHDLMMCIPCALQKAJRABKQTDQKCDQNKBIRJDRLPAJUDCROTDONIDSQOCMNHDIMRDHRDCTLSAIVGBTRNBMSJDQMSCOSGDRRQBOOIAGPPAKWFCHSMBKYFCUMNANOBAHKRAPTAB",
            "MMUANNODMLFCPOLBLKHCPQMBPTEDJJTAQVGCMPLDILEAPMRCRSABOSNCKPCDPQSCRWGBMNPAQMADSSKAJQBCRPQCSXHAHRTAJHFBGMNAPSDDPXQCJNIAIPSCPKIDFNOBFLBAOVRCTQCAEMKCPYJANTMBFPDB",
    };

    /* Test placements, adapted from TestUtility so that the next move is for the red player */
    static final String redTestPlacements[] = {
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAKJLAPPABQKMCJNECRLRBLQGBNPKBLPHDPRPBJOFAMRRDOKHCMINCOTGAQITDTIIBRPKCKIDCRSOBTPCCSRQASGCAQKPBQUADPRLCQNJAIPSB",
            "MMUAMLODKNJDLPTDKOBBJNOCJMBAOLPALJEDJPNBQLFAHNNCLKHBLRLCNKHDHKKBKSGANKQAMJDBOJQDQOACKVPCPNACOPMDJOIAPLLBHJDCJLMDGIFCFOTDJXCCRPSCHVGAGQRDJVCBSNKBEIECLLRC",
            "MMUANMTDOOADNPRAPLBCNOQCPPDAPNRDQLCDSNPBPPJDPITAMPBBPRLBMQIDTMNBRSICQTLBULFBSKPDROCBRIMDPUHBPHQDPSJDMSNDPLDBMMOALLHDRHKBKPGBNIKAJLFAVLSDQUACWMOBTMEAVOMD",
            "MMUAKMPDKKCDKOKDKQIDMKPAKRGAJNOCNNADHMQDIPJCGQNDKMHAKHKAGMECMHQAJIABOLMDKTEAKWRDMSBCJWSCGOBDILNDPNDDOGLCFOFCNITAKQGALFMARLJBKYRDMYIANROANQDDSMLDGWFDHRTA",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAOJNBMPGAQNQAINCAIQRDMJABNQOAJJDDJRQASOEBJTRAPPCBPRSAKPIBHILAUPICRMMDLPADUNTDRPFBURLCKIHDPUKDJMJCJQKDVRGDMHPC",
            "MMUAKMKAMKIAIMNDJKICJNQBNMEDIIQBGJDCFILCEJDBKNOCIGABIMMAHLGCKIOADKFBHONDJGHAEMMBEICALGKAMHAAEGPBCHCACLPBBMHBNOLCBPJDFLSCKFBCCNRAKFEBOIRDMOGDEETBBEFAEOSB",
            "MMUANMLDNNGAMOTBNQFBKMLDLLJDOLRDOLDBJMPCORADQNSBRLICJKQDPOHBHJKAKQEAGISDJRJCPNOAKPFCNSKBITACHPNDFIECROOAQQBBOSTAGQBAOJMCKTDDGMRDFICDPIPDSJICFLMCTHGAFQQC",
            "MMUAMKNAMPCCONMBNJDCNQSBLQFBPOPAOREANJRBQNDDPKNCOTBBORKBKKGAKMSAKRCBPTPDQUGBOOKDKPICMTTCQRIAOLRBSUJCLUMBKIAAROQARLJCRJLDIJBDOPQBKWHCHRTDTUHDMHOCGPABJNLB",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAMPSAMLDANKKCQPJCMSQCMJHCRLRCNSEDKPMBTLACNITDKRABTJPCQRCCSILCLHBAKUQDJIGDMFNAPHIBUJPDUHFCSIKBOFHDOKRDXJCBWILC",
            "MMUAKNQDLLCBJNNCLMJDHMTDNLJCONQCJKHBFLLDEJGAJJMDPLGAMOTALKHDDJOBGIBCAJRAJPBCKHSBDJCAMRNCRKABNOPCHKECLQSCMKFAGLPCNRFALQKBPKDCGPOASMIBJNMAOOAAGRRBKSEDOOLD",
            "MMUAKNMAIOIDMOKAMLFDJQKDIPBBOMMBPMDAMNLBLJHALPRBMQJAQOPCMLDAHMOAPQECSMOCORGAGMTCTNHCPONDPLCDNJTAKLJCGNPBVMEBPQSBVPGCLMLCRPFDQQQAGQAAQLNBTOABFSSCMIICKMQD",
            "MMUANLQAPLEAMKTDMOCAPJSBLQCDLLMDKOJBHOMDOPHBRKOBORBBHPKBMSFCPHNANIADQILAPNBBQGPDSHHAJKTAREJCJNODSFIBTEPASKGDNFRALUACHORATDICUHLATLEBNEQDQPDDPKNDIQDDRRKD",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAMKSDNJDDROQCMQGDMIKCNHBDJPPAJRIALGLBMOICPQLDPREAQHTCIPACOFOAOTFCRMRCPJJAMUQAGPHBEPMCGPHAMPKBSNGAKVPAPTAALSRD",
            "MMUANMMDOLFDLMRBLKDAQLTDPJHANNSARMIASJMCOPBBMQKCJMIBQMQBUMCCVNNBKLHCQPPDIOJDGONDIPGBUNODTHBCWLKBJPJAPQSDKJGDLOTBVQADUPPBQIFCFOLBQIDBTRLAOKEAFQOAKKADSNRA",
            "MMUAMOOBMOACKMTDNNGDOOMBORJCNKNAJNHDOSPBOIHBKOSBJQFANSLDKMCCJSKAMSABPMSBMQIAHOPAQLIBHTRAKUGDLVMBPHDCFOKAPTEAHMTAOUBBMJNAIRECPRQDSRCCPKRDOJJBKWOBNHBCMWLA",
            "MMUAKMRDKOICIMKDNOACOPTDPRJDMPKBPSFALLPDHOGDJQNCOQABMNQDSRDCKKLCKHFBQOMCTSHBHKOALJHDKGLDNICATUQAQUEBPVNBPSBDOOOAPXEBUVMASTCBUXPAGODBMHSBVZBDOHSCSRIBGKRB",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAONLBNJFBIMNDPQICHMQBQOJDRNPDJNABIPPAJKEDGNQBKQGDHKLBQRHDNRRCTMCCLRKBJHHAMTSCRPAATRMCLGGAURTDUNIBOIKAFMDCLVRD",
            "MMUAMPOCNPDAPOKARNGBQMMDLRIDSPTDJPADKSRDTNFANNLANRHALRRAQMHAIRTAHRDBUONARLEDQPQAIQIDHQQCSRJCJNMDRJFDHNSBSTBCSVPCMKCASWOBILJBNMKDURACWRSCYRCBHULDUXECWNNC",
            "MMUANNMAOPEDPONDMLJCNMKDOLBDNQSCKQFAMOTBKSAAQPQAQSBCSRPCKMAAQURCTRHDUSSBPKIDITLDRVDDQINAPVJARWOBOXCDQGMAJOICRYOBIVGCTRPCSSEBSKRBNSGBMNTALUCCMWKAWRDBQMLA",
            "MMUALLKBOMICQMLCOOGDROSCSPECNOOBRLADRINBSKCDVKKCLOGBRRODSNBDQRQBUOJBMQTCPHHAPKQCUMIAQUMDOSDAMRTARVFBPGPDTVECNUSCJNJAPDRBNLCCQRMAIODBJLNCPVABRYPCRTBDVORD",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAQNPDILBASMLBQOIASKKBINABQKLCJPCDPIQATOIBQHKDQNCBPKRDROADPGNBMPHAHOMBJKJCKJSDLRHCIQTBKTDDLPQBSJGCEORDSHEDOEPA",
            "MMUALLPBKNDCJLRCIOGAKJKAKOJALITCMKBDJJNCKRACNHRCKMDALRODJGHBJKKAPHHBNMTDINGCJTSCKSJAGJMDQGECRHLCFHFDPLNARJEDQGQDGOBANQQAFIIBTGSCKVICUGPDEPCANNOALFABRKLB",
            "MMUALORAMLIDOLNALRADNPLCPNJBKSODKUFDPPRAORJDNQTBMRADNPKBOSGAKLNDPJBAJLPBSPHBHKSAPIECMTTDNKFDQTQDJVCCITMAQRHDIWSASOIDLVOBLNCCUPMCHYDDQKQATKECKPPBLRGCSILA",
            "MMUAOMTBNKDALLNAMJIBKKQCMPACLNPBMHFBNOPDKHCBNLMBOJDDQKKDNPIAJJRCQIBCNNNAPIEBJGLDPOHARIMAPNGDHKODIMJCFMSDKQECTIKAKRBASLSDVLJBJHLDGNCBNSRDIPFBJDOBGKHCLGTD",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFARMKCQNCAQNQBKQCDPMKDQKHBLJOALQICPIRBOHGDQMRBKRJANRLCJSECLHSBJGIDPPQAJRFAJTPBLFBCJPMCJJGAINTBMDHCQGLCHGDDRLPB",
            "MMUAKNPAKMADLOSALQJBLKOAMSHDKOMDMPGALTQDKRFBJOLBKTIBMSLAOMICIUTCJNBCKKTCFUJAOQRBNTEAJJKCEVABIKQBFVECLJNDJVDAPQRDJOBCKXSBMMGBEUMCIYFDOUOAGVCAQMPBKOCCHXNC",
            "MMUALMSCKODDONNBOPGBIMRDLJJBHKKAHICBKPLBNJFCOIODHPFAGMOANGGDOGMBNEEDIKMBMCIAPMQCMOEARMPBPPHDIQTALQCAMETBQQHBINLAKKABSLPCKNJBNNSAKLBDPMKDGQACRSQCPHDDMGNB",
            "MMUAMORANMGDNQLCOOIDKNTDOKAAJOOCPMBDMKNDKLDCLLNDKODANRTAMLCBOLKCOPCAINRCQOJDOQOCLJJCNJSDRQECTQMBLRECTTQDKTBCUUPBMTHDNJSBNVGDISKBKRFAVQMCSOADHLQDRUHDWULC",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAQMPDROFCJKKAHLJAKKNBLKHDFLQDSMDDUNPDRPGBWNLAUMBBNKTDJOEASOSDMOACFMKAGNIAHORAYNHAJJRCGMABRROBEKGBJQMBLOCDPRLC",
            "MMUAMPTDOOGCOQKCMKEANLQAPREDQLPBNSFCRLPAQKADKLOATLGDQLKCNUHDTNSDPKHCPMRBMUICQSSBRSJDLKMCUSDBUQLAVOBAUJRAWLFBPONAUIIDWSNCKOCDSQODRNABJLQBSTCAKKMAXRJDOILB",
            "MMUAMPMDLPDCMQPBLMICLKQCORJCPQTAQRIAMTNDKIABLJKBMLHDMRLAJPCBJORDNNGALUSDJMJCMQTCKRFCJPSAMTBARTQDPSEBKTMCIOACPMRAPLECUTNCNVBDGPPAIRFCTUOASSHDUROCUODAEPKA",
            "MMUAMPNCJPEDKRTCKOBDNOPDOMICNQSDNSDCHRNALLGDNNLBISCAPRLBIRBAKJPBPQADGSOCPNEAIKRAHTJBPMKDIJGCRRQBSNFCGKKCMQJBKTTCROAANPMBHVHBPOMCUOHCIGOBJWFBESRBHYDDUPSA",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAOONDQLAAJPODHPIAMJQAHOFDGMKAPQECOKSDFQHAGSLDMHDBIRMBQLIBGOLBLQGBEOPCHNCCJTRDNIGDMHRAGKAAGRPBFMBCHTKBJMCBMPQA",
            "MMUAMKNBMPCCKNPDJODCOLLBHOECOKPDLRACRKMCPIEAIQODPJDCGPOAOPJBSISANRBDGNLCHNFBSLNCKJHDTMSCFLFANQKAQRJCUKTCILHBDMQDGKBCOORDVIGBTOTDOOAAJLQAJICCQORDKTIDPTKD",
            "MMUANORCONDARNTCNOADTMLCNMFDLLODNKFCQLSAQJGANPQBMQBBPIRAMQHAORQDQRJDLSNAUMDDLVOCONGDJKKDSSABIKNCJHHAQHSCLMIBJUPBRSCDWNKBJFIBJWTAQOBASJMAXOEALWMAOTEDIXPB",
            "MMUALONDMQJCLRTBLMCCKLLCNPIDKSNBJKDCKROAIRHBPPMDGQHBRQKDQQFBEPTDNQFAPRQBSRCALNRDTTBDQQMDDOABPUQDOUJBMSSAKIGBNRPADRGDDPLDOXBDUSODMPDANNSDWREBKOKCGSEBNKPA",
            "MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAMPPANJCBQPODJLHCSPPDRQEBSMRALKADNNTDSRGAMJMAPPIBGLQATTACJNKBTLFCELLAVRGCJPRACMBAMLSANQJAAMQDJIDAUPKDIJICVMNB",
            "MMUANMNAKMDAJOOCMLHCIMRDGMFDKOKAJLCDQMLCNOBBFKTAHOECJIKBIPJAFOPCMIEADKSAJHDCFQTCNRGCESNDPNAASLRBKJHAHKQDDRACMGMBSNJBKILDMPGBEUMDOPCANGSDGHIAJOPABLBDBIOA",
            "MMUANNLANMBDPLLCKNEAKPOANQHDLMMCIPCALQKAJRABKQTDQKCDQNKBIRJDRLPAJUDCROTDONIDSQOCMNHDIMRDHRDCTLSAIVGBTRNBMSJDQMSCOSGDRRQBOOIAGPPAKWFCHSMBKYFCUMNANOBAHKRA",
            "MMUANNODMLFCPOLBLKHCPQMBPTEDJJTAQVGCMPLDILEAPMRCRSABOSNCKPCDPQSCRWGBMNPAQMADSSKAJQBCRPQCSXHAHRTAJHFBGMNAPSDDPXQCJNIAIPSCPKIDFNOBFLBAOVRCTQCAEMKCPYJANTMB",
    };

}
