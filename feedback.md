# Assignment Two Feedback

## Group memebers: u6019071, u6060661, u5807060

## Tutor Comment
On the whole, the documentation of your code is sufficient. You consistency provide method level documentation, not always using the javadoc syntax, but there are other classes (StratoGame for instance) that could use more in-method documentation.

Your board class appears well designed, particularly the use of multiple groups to manage your JavaFX Scene. There is repetitive code in there, such as the initialisation of common properties like fonts and callbacks, which could've been simplified using additional classes, or methods which initialised the common properties. Alternatively, methods like initialSettings, which is ~600 lines, could've been decomposed into more focused methods. The use of CSS to style the GUI was a good choice.

It looks like great effort went into the design of your AI. Your additional JUnit tests were also well designed.

## Mark

### Manual mark
| Task | Description | Mark |
|:-:|---|:-:|
|8|Basic game      | 1/1 |
|9|Display scoring | .5/.5|
|11|Simple opponent| .5/.5|
|12|Good opponent  | 2/2 |

#### Manual mark /4

### Test results
| Task | Test | Result |
|:-:|---|:-:|
| |Compiled|1/1|
|3|TilePlacementWellFormed|5/5|
|4|PlacementWellFormed|7/7|
|6|PlacementValid|3/3|
|7|ScoreFromPlacement|1/1|
|10|GenerateMove|1/1|

#### Grade 7.0/7
### Deductions
| Description | Deduction |
|---|:-:|
|All files correct                | 0/-1 |
|Authorship clear for all classes | 0/-1 |
|Appropriate use of git           | 0/-1 |
|Classes compile correctly        | 0/-1 |
|Program runs from jar            | 0/-1 |
|Appropriate use of OO features   | 0/-.5 |
|Presentation pdf complete        | 0/-.5 |
|Program well designed            | 0/-.5 |
|Comments clear and sufficient    | 0/-.5 |
|Coding style good                | -.5/-.5 |
|Appropriate use of JUnit tests   | 0/-.5 |
|Design and code of very high quality | -5/-.5 |
|Works well and easy to run | 0/-.5 |

#### Total deductions: -1

### Final mark 10/11
## Originality statement

* The CSS code for styling buttons was heavily adapted from <http://fxexperience.com/2011/12/styling-fx-buttons-with-css/>

* The code for all variations of 'addPane' in class Board if heavily adapted from:
* <http://stackoverflow.com/questions/31095954/how-to-get-gridpane-row-and-column-ids-on-mouse-entered-in-each-cell-of-grid-in>

* The image of the mute 'button' was from <https://pixabay.com/en/icon-loudspeaker-speaker-horn-1628258/>

* The sound emitted whenever a placement is made was from
* <http://www.sounds.beachware.com/2illionzayp3may/jspjrz/SWITCH.mp3>

* Some of the instructions were copied from <https://boardgamegeek.com/boardgame/125022/stratopolis>

* The Stratopolis logo on the first game screen is a variation we made of the official logo.

Signed: Manal Mohania (u6019071), Joseph Meltzer (u6060661), and Zhixian Wu (u5807060)

## Git Log
```
commit f3ddf48d0894d6ff035c79f59823cef56985370d
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Oct 14 11:33:27 2016 +1100

    verdana heights look better

commit 248096b2b992293429d915470e12d5aec63d07a1
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Oct 14 11:29:06 2016 +1100

    presentation added, final push

commit 012e6fc8d35beae0df23324ae15592b75f1c4f59
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Oct 14 11:26:05 2016 +1100

    code styling

commit 6490107d2b51d99d4863bd089d16eca8f99d0388
Author: Zhixian  Wu <u5807060@anu.edu.au>
Date:   Fri Oct 14 10:58:08 2016 +1100

    changed instructions text, rebuilt project

commit 298abba4793859d8099fec232186dc33efb12d4d
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Oct 14 09:56:31 2016 +1100

    rebuilt project, comments

commit 67aefe6aa95ce3255cfb611e284848e8480ceecb
Author: Zhixian  Wu <u5807060@anu.edu.au>
Date:   Fri Oct 14 09:41:16 2016 +1100

    changed Green and Red to Verdana, made both unbolded when game is over, changed scoring font so not out of bounds when 3 digits

commit 5b36340f91bdad91bf38e82496947e15201d8486
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Oct 13 23:36:48 2016 +1100

    New UML Diagram

commit 9b4c632f442dffe0edc384c059487f398265fc20
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Oct 13 23:04:47 2016 +1100

    Winning text layout

commit 8fea1c2ebe28f0870f6573c42ef1bbfac2a8c778
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Oct 13 22:59:33 2016 +1100

    Winning text formatting

commit eba0acfcff5979bb3801bdf74ced2f51edbd0394
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Oct 13 22:22:03 2016 +1100

    Added strokes around winning text

commit 5d51920ddd9990e2e1964929e8f5258be9b8ca85
Merge: de28b21 0b3beb6
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Oct 13 20:12:45 2016 +1100

    Merge remote-tracking branch 'origin/master'

    # Conflicts:
    #       game.jar

commit de28b21d2cfe06f5145efde8036af0b10b149407
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Oct 13 20:12:35 2016 +1100

    Corrected few comments

commit 291e6a525d82f784571a221503c76405e648ef5b
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Oct 13 20:03:21 2016 +1100

    Added few comments

commit 0b3beb6a621038167b135b7bf29ebbd60e320f1b
Merge: caf7166 8393d41
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Oct 13 19:44:20 2016 +1100

    Merge remote-tracking branch 'origin/master'

commit caf71667b0e805cf218a041872e03c887994fc9a
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Oct 13 19:44:13 2016 +1100

    Comments for move generation functions
    Edited GenerateMoveTest
    Fixed small mistake in AI Test
    Fixed jar manifest file

commit 8393d41bc26bc5346cc5233c55886fd4ce37156c
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Oct 13 19:17:24 2016 +1100

    more comments

commit 614937f10d3fb9fdaf0b2bc6a5e3cfe1188c263a
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Oct 13 19:05:39 2016 +1100

    comments

commit 12e0c80db6821c4b186d3d465451e49543e8ed77
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Oct 13 18:26:14 2016 +1100

    Comment formatting

commit 876893433ef97a82350e89d298a37fdd25d4f8be
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Oct 13 18:15:36 2016 +1100

    Changed StringBuilder uses to concatenation (automatic fix)

commit b341c3aebe86c22addf54f352904aef3cc658cc1
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Oct 13 18:14:06 2016 +1100

    Comment formatting,
    Removed FIXMEs

commit aed7c60b1d09ec3961229b7540900938107c9791
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Oct 13 18:06:56 2016 +1100

    Comment formatting,
    Replaced some repetitive code with a loop (main menu button formatting)

commit 487635f6b0fa92d6750089cacf3f99a65ff094eb
Merge: 1b8c229 4a9f012
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Oct 13 17:47:55 2016 +1100

    Merge remote-tracking branch 'origin/master'

    # Conflicts:
    #       game.jar

commit 1b8c229b422ff84a181b8b20e9378c969c1337f9
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Oct 13 17:47:44 2016 +1100

    Originality, Best code, AI Test updated, code cleanup

commit 4a9f0128bd435502a98f6ee69adbb9eccc9df3ca
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Oct 13 17:39:59 2016 +1100

    comments

commit b1dd0a7b858b5cf7bf6cfda7089446bc8c19b80e
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Thu Oct 13 14:30:01 2016 +1100

    Fonts, alignment, cleanup

commit 6d48c2d596ae4e2f0b78a51126b01a9144855afe
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Oct 13 10:55:33 2016 +1100

    Standardised font to arial

commit e2ca6d943518cfc07dddd420e61d904f6d5f9b9c
Author: manalmohania <u6019071@anu.edu.au>
Date:   Wed Oct 12 23:01:01 2016 +1100

    Updated originality, added best code

commit 9139fa040a808116d012e4acb31cf0739c61e9fa
Author: manalmohania <u6019071@anu.edu.au>
Date:   Wed Oct 12 22:54:56 2016 +1100

    Minor edits

commit 855e459e59155b6418dcfd796d39414dd13b4815
Merge: 2429da6 7c364e9
Author: manalmohania <u6019071@anu.edu.au>
Date:   Wed Oct 12 22:53:58 2016 +1100

    Merge remote-tracking branch 'origin/master'

    # Conflicts:
    #       game.jar

commit 2429da6eeb986871f0d2551c773b3520c63d52eb
Author: manalmohania <u6019071@anu.edu.au>
Date:   Wed Oct 12 22:52:51 2016 +1100

    Minor edits

commit 7c364e93a06138952181b9f76b2f8140414e1017
Author: u5807060 <u5807060@anu.edu.au>
Date:   Wed Oct 12 22:47:43 2016 +1100

    comments

commit 0cf599592e25daac601071e88e98f36550df3fff
Author: u5807060 <u5807060@anu.edu.au>
Date:   Wed Oct 12 22:31:11 2016 +1100

    added instructions, updated G-feature and G-originality

commit 8ac3ad2fa581443ffb7c4ad816ebed7cf7c8d1d1
Author: manalmohania <u6019071@anu.edu.au>
Date:   Wed Oct 12 18:28:50 2016 +1100

    Minor edits

commit b0825235a93a197ebc7a889f02df6c76ffe364d3
Author: manalmohania <u6019071@anu.edu.au>
Date:   Wed Oct 12 18:13:02 2016 +1100

    Removed unnecessary functions and added comments

commit 82009e32c09bcf604c5087c92ea0a67ecad45b9f
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Oct 11 20:32:30 2016 +1100

    Added wav format for sound - this should hopefully work on the lab machines

commit 18a0e38e9418270e45efb7d79ebc6f999f018ff7
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Tue Oct 11 13:32:01 2016 +1100

    Jar building

commit d677faab89e2e8ab68f2a5334965637edf890e38
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Oct 9 11:13:23 2016 +1100

    Changed background colours during gameplay and main menu

commit 16512a923637ddd718b42c344e5ca3ba56c74d4a
Merge: 7efd35a 642b966
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Oct 9 11:09:47 2016 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 642b966222a166f71cabd25c824515a1e518ed14
Merge: e717679 231b44c
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sun Oct 9 09:57:08 2016 +1100

    Merge remote-tracking branch 'origin/master'

commit e717679db75ad8ee5e11ce641457c4f3113f0f85
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sun Oct 9 09:56:45 2016 +1100

    fixed sound bug

commit 7efd35ae217637b3ed391e8d6181a8b3a5a1acfa
Merge: f7a9fec 231b44c
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Oct 8 12:46:49 2016 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit f7a9fec708acb582a196a6e8d042d3c902e65474
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Oct 8 12:46:40 2016 +1100

    minor changes

commit 63754395df8b5f8b78f2bea67087cc7eac8c35aa
Merge: 4bd90bb 65da0b5
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Oct 8 12:46:07 2016 +1100

    minor changes

commit 231b44ca86a8af52c5f7a577f142a95fb94364af
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Fri Oct 7 20:02:21 2016 +1100

    Streamlined isTilePlacementWellFormed and isPlacementWellFormed, reduced calls to toString.
    Combined tileStraddle and areColoursAlright into a single function, reduced calls to toString.
    Implemented tile rotation on scrolling. Scroll down to rotate clockwise, up for anticlockwise.
    Some cleanup of AI code

commit 4a5e2b79f0918275b5b8d10af9ad5eda23bcea42
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Fri Oct 7 18:07:21 2016 +1100

    Removed redundancy in definition of 'genMoveNotEasy' and
    Set the 'Next Move' button to disable at the end of the game.

commit 65da0b56de681d0c96fccb31bf26bf9254549d4b
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Oct 6 17:46:50 2016 +1100

    more cleaner code + comments

commit 0b6ec28de002a9567e0f254eb09e4f9393a9d184
Author: Zhixian  Wu <u5807060@anu.edu.au>
Date:   Thu Oct 6 17:23:32 2016 +1100

    cleaner code + comments

commit 6ee95141f6d167809ce208ae36e7170c9298a492
Author: Zhixian  Wu <u5807060@anu.edu.au>
Date:   Thu Oct 6 16:47:57 2016 +1100

    comments

commit 4bd90bb5ea11bf1f3dae818e16e37d230836aef5
Merge: a283241 2e29a11
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Oct 6 14:51:52 2016 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 2e29a1123173e26b16d82ec72ad098380432cfd5
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Oct 6 14:51:24 2016 +1100

    fixed sound on/off bug

commit a2832411401befafa463132bf339d8f841e5382e
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Oct 6 14:29:23 2016 +1100

    Changed cheating to nightmare

commit 7788b8c2f56db4797008a0dac1524e72d47d9ab7
Merge: e266b4a 8f41311
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Oct 6 14:28:56 2016 +1100

    Merge remote-tracking branch 'origin/master'

commit e266b4a03af4ed85ad5cf4e074fed2c787780710
Merge: 036760b 65afcc3
Author: Zhixian  Wu <u5807060@anu.edu.au>
Date:   Thu Oct 6 14:27:29 2016 +1100

    Merge remote-tracking branch 'origin/master'

commit 8f41311a9b49a1e9aa88cfeefcf453cec7f88bad
Merge: 036760b 65afcc3
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Oct 6 14:27:29 2016 +1100

    Merge remote-tracking branch 'origin/master'

commit 036760bc40d2907e6cc3bf988a8c27dc909e7a4a
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Oct 6 14:27:19 2016 +1100

    cheating ai

commit 65afcc34214028f989ca14f3b3c6643f9f7ad76f
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Thu Oct 6 16:19:47 2016 +1300

    Cheating AI max depth back to 3, from 4

commit 6de410d2ff9607e4f7228b6a6fdbeb605474ba7e
Merge: 160583a 97fc580
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Oct 6 14:10:03 2016 +1100

    Merge remote-tracking branch 'origin/master'

commit 160583a93f03822e49f31676b95272eccb16d728
Merge: 437d30e ed967ae
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Oct 6 14:09:53 2016 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

    # Conflicts:
    #       src/comp1110/ass2/gui/Board.java

commit 97fc580920552ab29a12e099079f4e63519be8eb
Merge: d2a40a9 ed967ae
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Thu Oct 6 16:08:42 2016 +1300

    Merge remote-tracking branch 'origin/master'

commit d2a40a9ff4e8b7c9bd77d1a7aff557f090ad09c1
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Thu Oct 6 16:08:22 2016 +1300

    Changed move ordering for Easy AI

commit 437d30e6d390f86a2bf8d448a575251849ca1bc6
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Oct 6 14:03:08 2016 +1100

    minor changes

commit ed967ae2c99a9c741c39b65691de78a3ea2630e9
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Oct 6 13:51:18 2016 +1100

    debugged ai vs ai game, grey out unused rotate buttons

commit 225c89eceb1514200303d324916885e958e6c25f
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Oct 6 13:42:14 2016 +1100

    Turned on shuffling; minor changes

commit 3f90c71fb213214187e08816c1ece359d792c7f2
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Oct 4 16:59:47 2016 +1100

    Added comments

commit 3e5f1ea663b112e536d785f03f1819b00fa3726e
Merge: 894a87b 63ce394
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Oct 4 15:38:55 2016 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 894a87bae9934c31f07fd8fb23d808023377438d
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Oct 4 15:38:47 2016 +1100

    Changes to the main menu design - I'll make the start button blue after a while

commit 63ce39463fa7cd89c496aa04f1b3e0184a2d8fef
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Sat Oct 1 22:58:08 2016 +1000

    Centered "Score" label

commit 48c1e020b56d4fc60217c666cfbdcbb53dfcaf62
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Oct 1 19:44:01 2016 +1000

    Shifted error message below

commit b66489e83dc57355f72bee0f601442f56446bed9
Merge: 3a45082 6ebc6cc
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Oct 1 19:42:27 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 3a45082a2e4e930b5271ed49934facda2f3e8a40
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Oct 1 19:42:19 2016 +1000

    Addede light background, shifted buttons on the home screen 30 pixels above

commit 6ebc6ccbe132b6a84c91fe138f79102e13b0680e
Author: Zhixian  Wu <u5807060@anu.edu.au>
Date:   Sat Oct 1 19:38:23 2016 +1000

    de-emphasized tiles left

commit 46f869a8e1732abac2fdfce4792fb9a98d4bfd2c
Merge: 9f516b6 b596e1d
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Oct 1 19:37:01 2016 +1000

    Merge remote-tracking branch 'origin/master'

    # Conflicts:
    #       src/comp1110/ass2/gui/Board.java

commit 9f516b65dd58fe2199c62cf839eef66a04e894ca
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Oct 1 19:34:54 2016 +1000

    de-emphasized tiles left

commit b596e1d94d9446ccf45ab0f38e6cfc134327bbf8
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Oct 1 19:34:54 2016 +1000

    de-emphasized tiles left

commit 0b41414f3eecce9e036ff7abc976785afb2e64c8
Merge: 1647578 8b9314b
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Oct 1 19:02:36 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 8b9314b8492621ddafda39bc17a662647be5ce62
Merge: 723113a f576a80
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Oct 1 18:33:01 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 723113a6f766a6c8330e2e6c68bd42d3093ee13d
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Oct 1 18:32:55 2016 +1000

    opening screen, added images

commit 3e3e40686b9878cea3efa1c027059bf9e570a879
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Oct 1 18:31:24 2016 +1000

    opening screen

commit f576a803ed91a8d3b6d00e3f83421c5bf2d1341f
Author: Zhixian  Wu <u5807060@anu.edu.au>
Date:   Sat Oct 1 17:58:42 2016 +1000

    try 4

commit 0b3a002207e682de66624d6db2b977227f9e1f80
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Oct 1 17:58:20 2016 +1000

    try again 3

commit 1647578debf426abcec037834a81af1cf646923e
Merge: 80f1605 8ee8cd5
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Oct 1 17:55:38 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 8ee8cd5fe0287e3555922a601d558b6d70df4298
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Oct 1 17:35:40 2016 +1000

    try again 2

commit f131ffb97eb94771ee121e4feb64ea375cd5cae8
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Oct 1 17:30:47 2016 +1000

    try again

commit 80f16055a8a6c6a711bbc820db35b1c140da6eb1
Merge: d9e54bc 0f16e37
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Oct 1 17:27:10 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit d9e54bc95d54b6f61778ee58dc6c7c475a7fa245
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Oct 1 17:27:02 2016 +1000

    Sound is played on piece placement

commit 0f16e379f6e32d02215a687d5d649f18fdc5fbca
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Oct 1 17:26:12 2016 +1000

    running tiles count

commit 073fab7b148d62c6b0f32ab75c01cfd9035cadc3
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Oct 1 16:44:30 2016 +1000

    made mute button

commit ca9f6cdbc37f683c75b9c7bdb55a7bdc74754bb6
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Oct 1 15:56:13 2016 +1000

    another way of doing instruction popups

commit a7d283117ba775d8178f89438331418dd96e80ce
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Oct 1 12:23:55 2016 +1000

    Sound is played on piece placement

commit 04689efce7f3fe5abba1da55fde3f23e6dc715ed
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Sat Oct 1 10:43:48 2016 +1000

    Fixed AI termination conditions

commit ff6eac5f8b5638a4d91111f4ec9d4b9c9ae893a9
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Oct 1 10:24:38 2016 +1000

    fixed bug with ai

commit 6af946f286b62b706a1b9967e8a8e8550915a287
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Oct 1 10:14:56 2016 +1000

    Fixed bug with instructions

commit d34bccc0e3e9e01b3ed9105182e9b45be5649973
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 30 21:10:04 2016 +1000

    Fixed bug in instruction apperance

commit 45573c1bbe1239291e6a9ff127e0c57e80859827
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 30 21:07:36 2016 +1000

    Fixed bug in instruction apperance

commit 8a61048fe214cadf83521213f9425099deafc290
Merge: a15787d ac5452a
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 30 14:29:56 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit ac5452ae2ea35502e38b74298dff332f6793942b
Merge: 0954c08 7dce12f
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Sep 30 13:12:43 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 0954c08cca74ecc9b822590c26c3150595f6fca3
Merge: 5c973ea ce65f13
Author: Zhixian  Wu <u5807060@anu.edu.au>
Date:   Fri Sep 30 13:12:04 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 7dce12ff2d60c09431c54a5efc319a70953751b1
Merge: 5c973ea ce65f13
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Sep 30 13:12:04 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 5c973ea2093f3a3f1d036006d0eacc2808212f8b
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Sep 30 13:11:58 2016 +1000

    condensed some code into makeAIMove(), minor graphics changes

commit a15787d3c0fb0b8795996ff0068db051c5ce1e19
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 30 13:08:13 2016 +1000

    removed orint comment from scoring

commit ce65f139ad8810a36c94a3ca8709d3cadeb38538
Merge: 0cf01f1 8dd676b
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 30 13:06:06 2016 +1000

    Fixed bug in scoring

commit 0cf01f1de8d67ba4b78b226bf5036ecbfb4c6c12
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 30 12:42:25 2016 +1000

    button design

commit 8dd676b642eeb4f85c6cc3c7bf77cbbe7440d68c
Merge: 1a4c5a3 0b1f63e
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Sep 30 12:15:54 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 1a4c5a3c92191c9fcf6dc9e27f8ca3501b22884d
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Sep 30 12:15:46 2016 +1000

    made a cheating ai

commit 0b1f63eac0c30e42b3421190f3d06ebc9289c5d8
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 30 10:30:40 2016 +1000

    Fixed isPlacementAdjacent2

commit aac3bac1aeeef67e4eaf998a2f23ba0a1b454217
Merge: 7e59768 6899932
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 30 09:12:54 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 7e5976858dbdb9ea9bead5c865ad112d925f54b8
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 30 09:12:43 2016 +1000

    removed the needless print statements

commit 689993289a25473ae891640efadf0bd1cb286a4e
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Sep 29 22:42:29 2016 +1000

    Fixed red AI buttons not controlling the red AI difficulty

commit fd386025c99e549ae79a1edbbd7fef2f81d600cd
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Sep 29 22:35:32 2016 +1000

    Modified the scoring class

commit ba41a788649aee65ca1b99970b0e26ded375faf0
Merge: 71e4bb9 eb2c3cd
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Sep 29 21:47:09 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit eb2c3cd6a0e08038d726784e82468e5c5c6f5fc7
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Sep 29 21:47:04 2016 +1000

    Modified getScore

commit 71e4bb97eff195efd03722885e086d09867a3ae6
Merge: 2ecdce2 cb871b9
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Sep 29 21:45:38 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit cb871b9da52801e7c2c71a3e03c0f129a91e1b7c
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Sep 29 21:45:20 2016 +1000

    Modified getScore

commit 2ecdce250d23001fcd1134a761145ca9feed4066
Merge: 7cd4ed8 44b0429
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Sep 29 20:37:15 2016 +1000

    Merge remote-tracking branch 'origin/master'

    # Conflicts:
    #       src/comp1110/ass2/gui/assets/C_b.png

commit 7cd4ed881965ab10a00e88cf4137bfffb8ce6d53
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Sep 29 20:36:38 2016 +1000

    Faster average calculation, fixed white box on C_b.png

commit 44b04291c5caa4d4d280d176e5e7addd3282908a
Merge: 1709890 83c00d3
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Sep 29 20:33:01 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 1709890b418134f48b6fce32e1f58bca5ee412b0
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Sep 29 20:32:56 2016 +1000

    more buttons + styling

commit 83c00d35ea69e4e28a3ff912c0760ef439de4bcc
Merge: ee7b243 4b3878d
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Sep 29 20:05:04 2016 +1000

    Merge remote-tracking branch 'origin/master'

    # Conflicts:
    #       out/artifacts/comp1140_ass2_jar/comp1140-ass2.jar
    #       src/comp1110/ass2/gui/Board.java

commit ee7b2430d81e347fb363c53c2492a319dfe1821c
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Sep 29 20:03:11 2016 +1000

    modified isPlacementValid

commit 4b3878d00ab57f684ff3d4618567e92abc13fefd
Merge: b66827e 26d4843
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Sep 29 19:47:06 2016 +1000

    Merge remote-tracking branch 'origin/master'

    # Conflicts:
    #       src/comp1110/ass2/gui/Board.java

commit b66827e8dd20c83d6343a23224a2a9ba64615b70
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Sep 29 19:40:42 2016 +1000

    made 4x4 playing modes, changed game over screen

commit 26d48437acec7eb5eedf7fd77eaf2d05c6e3651d
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Sep 29 18:52:54 2016 +1000

    Slightly faster valid tile search

commit 55f8bd903de0bb5c04d5ec50a4d1de68fa7789bf
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Sep 29 17:33:44 2016 +1000

    Some code cleanup
    Some getColours calls in StratoGame changed directly to the definition of getColours (to help with understanding the profiling)

commit 2ab9dc816913ab5eb51633349b8af7a2daa27332
Merge: 584724d 9eb53c7
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Sep 29 15:16:28 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 584724d2c37ca72a9f1a90e7bf0ac1e10c79304e
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Sep 29 15:16:21 2016 +1000

    AI now only searches through the set of moves that are adjacent or on top of currently placed tile.
    Much faster! Almost playable at the beginning of the game.

commit 9eb53c75ceb5b2f8ef58862bf805fea5cb120055
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Sep 29 14:30:43 2016 +1000

    fixed tile placement

commit 8eafc4a5101c4d2974cc299509eb2f348fd6ef42
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 27 20:54:31 2016 +1000

    Changed main menu button styling

commit 242ab7b4613d30d84d30aef283642769a0a4f9c2
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 27 13:17:04 2016 +1000

    comments

commit 5be93686873baad9830bae3c60ee78bb94483f7d
Merge: 5ccffa3 bdc2067
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 27 13:12:13 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit 5ccffa36d9a4298834ca58f53a8c481aa2eaa538
Merge: 971ee78 367b28a
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 27 13:11:36 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit bdc20675f1aecb3665dc13755aa922bb9434bcbe
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Tue Sep 27 06:18:16 2016 +1000

    Increase timeouts by a further 10x; they now have 100x of slack.

commit 367b28a31bbc6c17e861bb7df1bf90d1a7b9482f
Author: u5807060 <u5807060@anu.edu.au>
Date:   Mon Sep 26 22:13:47 2016 +1000

    comments + score offset

commit f25f065083edfb1bb08d7742bbcff88b1c67412d
Author: Zhixian  Wu <u5807060@anu.edu.au>
Date:   Mon Sep 26 21:40:49 2016 +1000

    minor variable renaming, rebuilt artifacts

commit 20ca74a1a1fd5543bfad93b78dc78a56faa8103d
Merge: 4b23a63 f62d788
Author: u5807060 <u5807060@anu.edu.au>
Date:   Mon Sep 26 21:32:48 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 4b23a63a223f3f716327812febf3c90a4a4b8fea
Author: Zhixian  Wu <u5807060@anu.edu.au>
Date:   Mon Sep 26 21:32:37 2016 +1000

    authorship comments

commit f62d7884c4a84d96047e252c52944776bc12f2f1
Author: manalmohania <u6019071@anu.edu.au>
Date:   Mon Sep 26 21:14:37 2016 +1000

    Added admin stuff and authorship for the viewer

commit a385183561a27babc5b58f90c8c726023f5ac0cb
Merge: 709c9d6 050dec1
Author: manalmohania <u6019071@anu.edu.au>
Date:   Mon Sep 26 20:55:03 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit 709c9d6347a3d8a6379e670fb43f72c3de14f9cd
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Mon Sep 26 17:53:10 2016 +1000

    Code Commenting

commit 03bd7e6477b61f0a08765ab0de9394f80b7d1727
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Mon Sep 26 17:22:48 2016 +1000

    Updated jar file.

commit ea82603830cf90a84aebd39bb398b09a0a012193
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Mon Sep 26 17:19:18 2016 +1000

    Added jar file to repo. Changed probabilistic AI to last turn only.

commit ee2fa6d34007a4444eda7780d66d0538d513d302
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Mon Sep 26 15:28:14 2016 +1000

    Fixed errors in AI code. When probabilistic AI kicks in for the last 5 moves, it becomes extremely slow.

commit 050dec19e3fdfa3699e04d1bce7f9e626875a1fb
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Mon Sep 26 09:20:08 2016 +1000

    Add timeouts to tests

commit d0485c1e01e8a92ac60d2e38e6ef589d2a0033ee
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Sep 25 22:10:43 2016 +1000

    Undone the change

commit 272ca190ca27d8d426f5c56e6323cb077bb5da63
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Sep 25 22:03:28 2016 +1000

    Got rid of a few warnings

commit 028e2880ccbfe90df20384f6713d8baed6593d03
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Sep 25 21:57:20 2016 +1000

    Merged isOnTop with isPlacementAdjacent

commit 4d2729f64be3f5520a7735c7bd9cf436c3ff66f9
Merge: b218cf1 1cb1f23
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Sep 25 19:53:23 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit b218cf1ce221e31fb1b080e943f634f688b8befd
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Sep 25 19:53:16 2016 +1000

    Trivial Changes

commit 1cb1f235e725ceedb9ddbf1a8c9e2fc7550c4db2
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Sun Sep 25 19:40:09 2016 +1000

    Probabilistic AI: will now look ahead to 3 turns on its last five moves.
    Search depths of 3 use probability to determine an outcome.
    Added a test to ensure that the new AI, when told to search to depth 2 only, returns the same result as the previous version of the AI.

commit aef2de64f813ff31fb15c3ce3de68363c658e24b
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Sep 25 14:30:14 2016 +1000

    Trivial Changes

commit dfed89bb6b261af368d454c893e066329b267c53
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Sep 25 14:21:53 2016 +1000

    Trivial Changes

commit c435104935570f49728c44a6cafb52b93422d750
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Sep 25 12:29:43 2016 +1000

    Trivial Changes

commit 5ba8a13f0d0f7a2724ff9b85c8f2978acb8e1856
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Sep 25 12:28:28 2016 +1000

    Trivial Changes

commit 46bd7b70c7789fdf550bb57dcbee29267d0d0b19
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Sep 25 12:23:20 2016 +1000

    Trivial Changes

commit 5efe99f3f876950228df0b53eaf6a50b4ca64375
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Sep 25 10:50:41 2016 +1000

    Fixed bug in scoring

commit b3d104db004198416348bc402065191b03728a17
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Sat Sep 24 23:04:44 2016 +1000

    Adde icon for application

commit 9782888fbb2f702a8c3624640eea6df8ef3bb428
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Sat Sep 24 22:58:22 2016 +1000

    Window no longer resizable, changed title from "Stratogame Board" to "Stratopolis"

commit 8273b083c6cfdd486b05c0b7c8921a8d690eb22b
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Sep 24 22:13:42 2016 +1000

    new board style

commit 45917b5e9fefb7ea22e40b0a9b550e5368c36718
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Sep 24 21:57:10 2016 +1000

    removed row/column labels, changed position of running score

commit e6c2d6cc6efc60f232f820b9570f2d1fb801fb34
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Sep 24 19:46:08 2016 +1000

    Changed background to white

commit a2b3d73885c9dcda8a64765b46e49ce1989cabd0
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Sep 24 15:39:44 2016 +1000

    Added comments and authorship details

commit 3f2154839b27faf0b2c1f72739dd21b93c4e0df5
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Sep 24 13:02:25 2016 +1000

    Added clarifying parantheses to an expression

commit 3a8820a2d902cf8c21a714a0106e733b48f6880c
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Sep 24 12:59:32 2016 +1000

    Added comments to the tests

commit 803973c9ad066fe6ae91dc106899aee4dff73454
Merge: 38dc122 0029dd3
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Sep 24 12:07:52 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 38dc1224ff70cb1f9c8e617a5be229cc6b9b0273
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Sep 24 12:07:28 2016 +1000

    Trivial Changes

commit 0029dd339ccea57e5db023cbbdea743e267fe092
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Sep 24 11:43:13 2016 +1000

    fixed bug

commit 80080f6710173afb925e63d3fa95ebbaba6002b3
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Sep 24 11:17:37 2016 +1000

    Added comments to the scoring class

commit 059c11059f5df31b9b7372bd4b2b681ac3688471
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Sep 24 10:49:06 2016 +1000

    Shifted the error message below

commit 9360c32d5873c62c687fa2260f83565e9affd809
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Sep 24 10:47:11 2016 +1000

    Added the word "scores"

commit c89e894333f15879353626bfff2e7be1473b783e
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Sep 24 10:27:14 2016 +1000

    Fixed bug in makeTempPlacement

commit 51a26d18cf9b00e4b8e57cd82c305b30fb7c619b
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Sep 24 10:16:22 2016 +1000

    Trivial Changes

commit b55dd47bae2c432027add780954f06c025283bc3
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sat Sep 24 08:20:11 2016 +1000

    moved updateScore() to makeGUIPlacement

commit fbc0e50602f6cd5a225461d91859b4d46d0fa612
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Fri Sep 23 23:55:17 2016 +1000

    Positioning of score tracker

commit bf88a4d1fdf966c762b0e752de013220946518a3
Merge: c8cd900 5ec232e
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Fri Sep 23 23:53:03 2016 +1000

    Merge remote-tracking branch 'origin/master'

    # Conflicts:
    #       src/comp1110/ass2/gui/Board.java

commit c8cd9002ba0aac13eb422a32a9cd7dcaa26c7837
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Fri Sep 23 23:46:52 2016 +1000

    Tweaked size and position of score tracker to allow display of two three-digit numbers without overflow.
    Also added some authorship notes.

commit 5ec232e9154a8dda634c5c4aa726dcc95626c342
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 23 23:26:32 2016 +1000

    Prepended the scores with zeros -- might need to revert it back, need to fix the rectangle width, and _probably_ need to add a partition between red and greens scores

commit ac26dc3bc8541dc5c86ecacb7e6e8c22a2a9a91b
Merge: 9d55937 fe8ddaf
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 23 23:11:22 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 9d55937df047e06dfdca2b8057b09dd3f30c15d0
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 23 23:11:16 2016 +1000

    Fixed width of the rectangle

commit fe8ddaf4cb0583883ff536ffc95087af03053b23
Merge: 3d06888 0858267
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Fri Sep 23 23:09:10 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 3d06888c188499cebb2d03b83253b12ee508dabc
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Fri Sep 23 23:09:03 2016 +1000

    Hover border bigger, preview shows on invalid placements too, but with less saturation.

commit 0858267e7eca563f78a21715f57c92db50d0cf81
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 23 22:58:35 2016 +1000

    Added design to score pane

commit cad79557c4dbf3caa2832ee39ae99343b19dcf86
Merge: bc2c9c5 1c67565
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 23 22:02:09 2016 +1000

    Merge remote-tracking branch 'origin/master'

    # Conflicts:
    #       src/comp1110/ass2/gui/Board.java

commit bc2c9c5aa5b01ab3093e768176ccca4f99a3dc39
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 23 21:53:27 2016 +1000

    Added a simple running score

commit 1c675651436c48d70eca75ca133d54293af8476f
Author: Zhixian  Wu <u5807060@anu.edu.au>
Date:   Fri Sep 23 21:39:23 2016 +1000

    did some authorship comments, made the see score button, deleted classes we don't need

commit cc302d4f75a77e57106659b78ba3f5b8b71ced8a
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 23 21:18:10 2016 +1000

    Changed positions of tiles and error message

commit 54bc4a14c7cb5bcac97ed64dede88aee51e78cab
Merge: 6b265be 8101869
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 23 20:47:48 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 6b265be8c60b56377f3092175a33a1e4c98f410e
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 23 20:47:36 2016 +1000

    Trivial Changes

commit 8101869989ff74bad5ca8b2c6c78468e401e67ff
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Sep 23 19:57:58 2016 +1000

    fixed main menu glitch

commit 3b6ec482b312225a8348e49a9ad75e8beee159aa
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Sep 23 19:38:55 2016 +1000

    main menu button a bit glitchy

commit 37dbef976f0f44f71d70d867aaf9aa8104fdedf4
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Sep 23 18:02:38 2016 +1000

    minor fixes done

commit e67530615e8aa6e78c8c5f9012f7de6781bd9e93
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Sep 23 17:46:25 2016 +1000

    minor fixed done

commit 4b1b9ae0e5b6789fda2279af48cf016b3039bb99
Author: Zhixian  Wu <u5807060@anu.edu.au>
Date:   Fri Sep 23 17:44:30 2016 +1000

    minor fixed done

commit 1dbd34546d5fee94febd4afb320e7f887b8ea0e4
Merge: 5ef825a c568362
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Sep 23 16:00:36 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit c56836240373c80a11396229d4d83267889d6b5e
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Sep 22 17:58:21 2016 +1000

    Changed background colour shade

commit 27633b941d5f7ea4d409581c88c7e7a1bc4d1353
Merge: f4f8450 098c02e
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Thu Sep 22 16:58:28 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit f4f84506a3d93e271eb40e48d0bf470648d2ee96
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Thu Sep 22 16:58:08 2016 +1000

    Ensure that valid placements always get tested in their complete form.

commit 79c7a488c1acef454f9ac9d5fbb49c9feb42f323
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Thu Sep 22 16:56:56 2016 +1000

    Added a test to catch placements that go off the board.

commit a62347c48f5c30b4c7a2ed35452b67a572779143
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Sep 22 15:18:31 2016 +1000

    Changed background to light grey

commit 5f2078e573ffdfda09aac389fcbb53ee6e8ba22e
Merge: 333ea85 55baa6f
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Sep 22 15:14:33 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 333ea85dec6c5c3ad35c6af417a25c0059387aa3
Merge: ddc9506 23563cb
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Sep 22 15:14:26 2016 +1000

    Added hover preview for single player games

commit 55baa6f26b6361d467961c99e9186d7674a1b01d
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Sep 22 15:12:02 2016 +1000

    Changed board tile borders to white

commit 23563cb4cdfef9d39bec237383c0d792df4d7db9
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Thu Sep 22 14:47:54 2016 +1000

    Placed and Hover variants of images
    Also hover opacity change

commit ddc950661d7b9c527f6ed9e90d9a6f53a8a7453f
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Sep 22 14:19:06 2016 +1000

    Fixed green player

commit b3f3984b602914816da1b67a08d5104bac56008b
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Sep 22 14:05:15 2016 +1000

    Added preview for one player games too

commit b03da630f9b45a7e6aebe21f492749725d58956a
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Sep 22 12:53:38 2016 +1000

    Got rid of a few warnings

commit 1743c3255b3f2307eeb0c53cb566dc1d7f1ddf1d
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Sep 22 11:42:52 2016 +1000

    Added preview upon mouseOver

commit 5ef825a8b7d897d315ceb6ed51df599e0d11ae38
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 20 10:56:38 2016 +1000

    this version definitely works

commit 971ee7810ccb2dd0e28c7672b56a374c13e45329
Merge: 66f9f9b 4b72b51
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 20 10:53:43 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 4b72b51662e40eb3ff9ad9b326a18dfe0be51067
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 20 10:53:38 2016 +1000

    update board.java

commit 66f9f9bc4d6548e3dbeb6394ff3d25c6e1e816d7
Merge: 2ba859c 098c524
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 20 10:47:56 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 098c524c0d3838773fa1c92dbf30c9ef77d43d30
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 20 10:47:48 2016 +1000

    reversion

commit 2ba859c7b73ecae6b720fd8eec0a4c98887020cc
Merge: ad58622 046c931
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 20 10:43:50 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 046c931275b9295e220207ebdfa5cdbb2dc76303
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 20 10:43:42 2016 +1000

    still trying to add outoftiles.png

commit ad5862242dc9d685a974b41e0b2b7d07d85472df
Merge: 9345692 765a329
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 20 10:41:13 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 765a329493d9cbd62a3ba5856badb733aeadf062
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 20 10:40:46 2016 +1000

    added outoftiles.png

commit 9345692e28e43299a5454d526b4d88d743acb2b9
Merge: c7496aa 7cf9f2e
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 20 10:36:12 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 7cf9f2e2b0759b9c25ca6702bca789adb4f96750
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 20 10:22:33 2016 +1000

    recommit Board

commit 29aeb365610cfd6c65948f7949394927bea1f6b9
Author: Zhixian  Wu <u5807060@anu.edu.au>
Date:   Tue Sep 20 10:19:08 2016 +1000

    Update Board.java

commit c7496aa80bcf6b730014f3b747ed65b8454f5134
Merge: 9f1f110 c492193
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 20 10:05:29 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit c4921930ab9805b6ce5b4b6bda13c958b1a3323c
Merge: f7ed8de d839dec
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 20 09:50:32 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit f7ed8dee6586418588f635d17fcc81ab3b20f797
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 20 09:50:09 2016 +1000

    recommit

commit 9f1f11039c34b257dd6ae57e0fdc52be40f90795
Merge: 6115dc8 d839dec
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 20 09:25:00 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit d839dec6912a10832684315077fb67e842db48a0
Merge: d25daf0 00ce1b8
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 20 09:22:55 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit d25daf06a67ce6db068aed83ee15eb35f1f2b28e
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 20 09:22:25 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

    # Conflicts:
    #       src/comp1110/ass2/BoardState.java
    #       src/comp1110/ass2/gui/Board.java
    #       tests/comp1110/ass2/PlayerTestByZhixian.java

commit f98ec352aa88c1d7614361985962e801e4e6d197
Merge: 253d348 098c02e
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 20 09:14:06 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit 00ce1b804ca61b680885d8c573fe48fd8958e469
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Tue Sep 20 08:55:48 2016 +1000

    E Originality

commit 3577c894a33d4ff8b631de58f7cc1c6d5298783b
Merge: 9ae0bae fc78fd3
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Tue Sep 20 08:55:24 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 9ae0bae4a87a7ab2667a291928fb30cc5e2c100d
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Tue Sep 20 08:53:57 2016 +1000

    Added another test

commit 253d34864ebd60a3a060c7c6a537d75f780cc4be
Merge: c31e145 fc78fd3
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Sep 18 18:02:58 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit c31e14513dac72c53cabbf8d3b139552a95ddead
Merge: 5bd49f4 19af3a0
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Sep 18 18:02:48 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

    # Conflicts:
    #       src/comp1110/ass2/BoardState.java
    #       src/comp1110/ass2/gui/Board.java
    #       tests/comp1110/ass2/PlayerTestByZhixian.java

commit fc78fd3c09f1b0a6dd8d783285b71b7d59cec3c0
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sun Sep 18 15:46:12 2016 +1000

    fully working, debugged, and commented

commit 17b6014f375b60e40e61760a6d52a39f804afac3
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sun Sep 18 14:02:56 2016 +1000

    added heights

commit 5bd49f4e1d7d1ebf7163a5cbe968c598cc9d6957
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Sep 18 13:49:04 2016 +1000

    Trivial Changes

commit 19af3a041b226b840f933e80be84b5efd2fb1fe6
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sun Sep 18 13:33:46 2016 +1000

    working smoother game + debugging

commit 098c02e515da24ce3725b05f4030979d414fff8d
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Fri Sep 16 11:57:55 2016 +1000

    Clarify scoring

commit 769ee8a1bcc7a85a95e749671113fcff8843ae35
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Fri Sep 16 11:44:39 2016 +1000

    Fix bug in scoring.   When there is more than one region of the maximum size, the one with the greatest height should be used to calculate the score.

commit 9cfaa8e41f03c0fc61b88e6469ba7380511ba213
Merge: 3912346 7ce9678
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Sep 16 09:56:55 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit 7ce967876b5dbb609eff609897f3b022dfb1264e
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Thu Sep 15 21:52:47 2016 +1000

    Added tests for tasks 7 and 10

commit 391234654562b8bc17fcf3cb94b9c5af47101b45
Merge: b71da2b 1cab5c5
Author: manalmohania <u6019071@anu.edu.au>
Date:   Wed Sep 14 12:16:02 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit b71da2b564fed4207b6c10b68f9a6865c43a3275
Merge: efbfdff 846a103
Author: manalmohania <u6019071@anu.edu.au>
Date:   Wed Sep 14 12:15:50 2016 +1000

    Trivial Changes

commit 1cab5c5e3870dc2d408db6e48e6721518a65444c
Author: u5807060 <u5807060@anu.edu.au>
Date:   Wed Sep 14 11:13:57 2016 +1000

    test draft Zhixian

commit efbfdffd07396f12f872b9c51d26e334ea8c01c9
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 13 21:12:54 2016 +1000

    Trivial modifications

commit 846a1031a6d4c5183fbd281ae5d7e0516cc427f9
Merge: 60e883f febd0c7
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 13 19:23:33 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 60e883f30ce2240b99b98571c2177498bd5de71b
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 13 19:23:13 2016 +1000

    streamlined and commented code

commit febd0c7f75d7da13aadea6cee2ff091e6d74bcf1
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Tue Sep 13 13:42:46 2016 +1000

    Created an AI Test

commit 2150372208f97f32aa99026b909249617d07782f
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 13 12:40:19 2016 +1000

    about to get rid of a lot of broilercode

commit ed3ad06484cec903d366b82c71bd8f6dab6c5561
Merge: e68a8a8 17cbbb0
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Sep 10 22:13:00 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 17cbbb0a9b85d7758a1921c936ec08763ae27d5b
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Thu Sep 8 15:52:22 2016 +1200

    Created packaging/artifact configuration for JAR file

commit e68a8a8ce88b8d83bd16d61383ff88c1e9fe65df
Merge: 4426270 6ac0237
Author: manalmohania <u6019071@anu.edu.au>
Date:   Wed Sep 7 14:23:08 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 6ac023786ad64389a1826017ba342a16bcff2aad
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Wed Sep 7 12:53:57 2016 +1000

    Task 12: AI considers opponent's potential gain from a move.

commit 4426270f65e43256db8927032414e6590fe9c806
Merge: 15e3760 3b13157
Author: manalmohania <u6019071@anu.edu.au>
Date:   Wed Sep 7 12:14:23 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 3b13157ec120421127b825707ef727ef07ca070b
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Wed Sep 7 12:12:40 2016 +1000

    Task 10/11/12: Fixed some bugs in generateMove, it should no longer produce empty strings, and it also executes much faster.

commit 15e37601de058b43908d89ac88db932eedb3e081
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 6 23:07:19 2016 +1000

    Corrected comments; reduced number of iterations in one of the tests

commit d17ad03025720f4a6d79f00d6fa48e4dc5935e45
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 6 22:24:08 2016 +1000

    Added main

commit 296a8828bf904da0ef04e79f5a8c50b6d180bbe3
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 6 20:40:19 2016 +1000

    Removed main

commit 07e21e896da5bb4d1223ac07c2d3de3230ada8a8
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 6 20:20:41 2016 +1000

    Got rid of warnings in the test section

commit 2721fc69267352049263a49f3ea6eeba6966c52f
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 6 20:19:32 2016 +1000

    Fixed error message in one of the tests. Also increases the number of iterations.

commit 9a96db4dd5416e5111c98a805dc6f3b62335316f
Merge: a395c68 9cae8cc
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 6 20:08:58 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit a395c68dde8026e739015fd8e2e74329ff3df2cc
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 6 20:08:47 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

    # Conflicts:
    #       src/comp1110/ass2/gui/Board.java

commit 9cae8cc2ba10add53ce24950653a1e0e3fd08053
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Tue Sep 6 18:24:43 2016 +1000

    Task 10/11/12: generateMove uses an alpha-beta pruning algorithm to return the best move.
    Currently it will search only two levels: one for each tile that is visible.

commit 140266f4feb87c77ecef285acdc2399f8919bace
Merge: 041479e 1f16781
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 6 18:08:17 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

    # Conflicts:
    #       src/comp1110/ass2/gui/Board.java

commit 1f167810739a2b84a47b1c38a83329d2ff736df0
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 6 16:56:24 2016 +1000

    push

commit 041479ef666ea8a6f5877d7db86adc3575a6ee33
Merge: f985064 9681f02
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 6 16:54:37 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit f985064fe0fbc21fa17e576108b06d2b2745c286
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 6 16:54:28 2016 +1000

    Fixed array bounds, added few tests

commit 9681f0205a46729535d9857baae22e99ab33c38e
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 6 16:35:48 2016 +1000

    finished basic gui

commit fcff240ee814532d223f08e1d2d03032f0c716b6
Merge: b73584d 19883c0
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 6 16:13:48 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit b73584d55e0a34c4980bef5825b121d1d6d56d21
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Sep 6 16:13:36 2016 +1000

    Fixed array bounds, added few tests

commit 19883c046116aab2e9dc99d3343571593c861b7c
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 6 13:17:40 2016 +1000

    all players complete

commit 5937213c6cb86f853e1b5a2949bde0c1d2b1ccd2
Merge: a508b52 3bbff6b
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 6 12:50:12 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit a508b5234f6b0f333d59505d0367668db71e938a
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 6 12:49:54 2016 +1000

    two player complete

commit a6ba65f46cc5c91d9a546457748ab90f3e88e70b
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Sep 6 11:56:10 2016 +1000

    checks for valid placement, control panel shows turns

commit 3bbff6b1e1fab458ffcc7aef46aa1142ddf42d43
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Sep 4 09:48:36 2016 +1000

    Fixed array bounds, added few tests

commit bada9331ca345bfa9e125022c4c6ca47d273ed4e
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Sep 1 15:13:56 2016 +1000

    fixed error

commit d623cb6227571a5ad66549ab25dc8298f95a2302
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Sep 1 15:13:06 2016 +1000

    restored class

commit 5bf12ef6b9c2cebbbac58040ccf24de1d81e5b36
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Sep 1 15:10:23 2016 +1000

    Merge branch 'master' of C:\Users\Aftran261\IdeaProjects\comp1140-ass2-tue13p with conflicts.

commit cba510c43483aa97b7acee5ac2ce260fdf3d5928
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Sep 1 14:20:31 2016 +1000

    Added PlayerR.java

commit 01cad3e8d3319846023f7bc0370f124671c29fef
Author: manalmohania <u6019071@anu.edu.au>
Date:   Wed Aug 31 21:39:35 2016 +1000

    Updated comments

commit bfe56b9b49d0acadf3286ef285e0118deefe04a6
Author: manalmohania <u6019071@anu.edu.au>
Date:   Wed Aug 31 16:33:43 2016 +1000

    Removed main()

commit 1328c0b2ad090f93691956744138823a17bb2157
Author: manalmohania <u6019071@anu.edu.au>
Date:   Wed Aug 31 11:07:30 2016 +1000

    Fixed bug in nextResult()

commit 6da12457be4b3d9235e123d7108a868d51b84ea2
Author: manalmohania <u6019071@anu.edu.au>
Date:   Wed Aug 31 11:05:49 2016 +1000

    Fixed an edge case in nextResult()

commit efb6abe9e4f6c7ff403e0a488abe1d6480cb4086
Merge: 7e4571e 07cc782
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Aug 30 21:45:51 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 7e4571edf46fd9fa50a1d15b0becac0a1b3f9abe
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Aug 30 21:45:38 2016 +1000

    Added hasGreenWon() method

commit 07cc7820fb10c9f17d12617610e168f3fddcf63c
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Aug 30 12:02:30 2016 +1000

    last bug

commit 2b2b98c122d2b2f80556a33a87a860c3466feef0
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Aug 30 12:00:08 2016 +1000

    updated originality statement

commit d183ab1ddffbb96f378384b1264139452923d56a
Merge: 7b5c3b8 da07f21
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Aug 30 11:55:36 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 7b5c3b8c4ce011979dad874fd4ac9a9a60bc0885
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Aug 30 11:55:04 2016 +1000

    fixed bugs

commit 391eeeedf7997d533f403665e3575722cdcdebc3
Merge: 102b891 da07f21
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Aug 30 09:44:08 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 102b891bf0220c851ebb7a875668c1ac1caa2b08
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Aug 30 09:43:54 2016 +1000

    Check Bounds

commit da07f210ad3b73f5d029d3ca941185a9cc9eac23
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Mon Aug 29 18:36:16 2016 +1000

    Joseph Originality

commit cec7e756265b1add1dc07b7a5f743412d4bf303d
Merge: 1715ab7 c51ba0b
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 26 16:52:26 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 1715ab7d94c45251f5d96a187eb4ab4c60d4b46d
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 26 16:52:21 2016 +1000

    Updated my Originality statement

commit c51ba0b1ab17a0647528bffbf71bc0d9a972ae35
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Fri Aug 26 16:51:55 2016 +1000

    Task 10: Generates a (slightly) intelligent move based on the resulting score.

commit 912157ffb8d4197d679308249b4b9f2ded7f82d3
Merge: d9b7a37 53b155b
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 26 16:18:25 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit d9b7a37af616d3314c701354b8cefddc95685448
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 26 16:17:59 2016 +1000

    Fixed bracketing in isPlacementAdjacent

commit 53b155bf8964ece9b48132f4610122ff9cf31669
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Fri Aug 26 15:22:58 2016 +1000

    Task 10: Generate a valid move

commit 76eb3e6175bc7de7dbc9da2a7bc94675580d99e4
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Aug 26 14:09:29 2016 +1000

    still bugs

commit 61b24c05e2d45d689b15b3c9207b95ce87e9b207
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Aug 26 13:40:53 2016 +1000

    Many bugs.

commit 3f51e7f19e19e2eb14a0bd4ee6071a9b979795ab
Merge: bd2662b 45abf6e
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Aug 26 13:21:03 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit bd2662b2662fe2b36aa9bbc239fdbee20655fd78
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Aug 26 13:20:40 2016 +1000

    Made placements. Two bugs.

commit 45abf6eddf32fb48e05a64bf1017587cd8d39586
Merge: 39c8e16 b3d31eb
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 26 12:13:32 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 39c8e16327ab1bbdc62da64813adfbf945b1a706
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 26 12:13:26 2016 +1000

    Removed stray lines

commit 54b93df8c24e1cb52ce8da4cb13db86c13587ce5
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 26 12:12:21 2016 +1000

    Removed stray lines

commit b3d31eb7a4d5e6fc86d03795f5c75c86a55adedd
Merge: a3fc19d 8200346
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Aug 26 12:08:09 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit a3fc19d3201b1d4ad303e9024c8e317b385b52d0
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Aug 26 12:05:59 2016 +1000

    made board

commit 8200346c9e6a39068eb7047e3bc8860f4e399560
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 26 11:26:10 2016 +1000

    Done task 7

commit c4b6145c9c7bf1be5619b2737bc3992ee8deaefe
Merge: 8f06f28 a3661e4
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 26 09:02:00 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 8f06f28a361f6b6d078100da667a34f20ae9663e
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 26 09:01:52 2016 +1000

    Able to identify the largest region. Need to work on the heights.

commit a3661e4e5286e49fef6d66032adb072de89bb427
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Aug 25 21:38:16 2016 +1000

    making controls

commit 364eeebacf5f8d97e5ddf15ce68c8c0f1f9821a0
Merge: 5b2f8dd 931a90a
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Aug 23 20:21:26 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 5b2f8dd4fb672f5600dc05ddc4f1cc8be53a1a1b
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Aug 23 20:21:17 2016 +1000

    Fixed logic for adjacency. Changed  a line of code in Viewer.

commit 931a90a97d558825db61edb34b3ac35f270808df
Merge: f64fd7c bc2495e
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Aug 23 20:19:26 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit f64fd7c31d1a0fb4b9b05ce1b5d666a35c9a2255
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Aug 23 20:18:32 2016 +1000

    Suggestions for Week 7 deliverable.

commit bc2495e575394959d6354c9bfc4bcdac35fcd5ef
Author: Benjamin George Roberts <benjamin.roberts@anu.edu.au>
Date:   Tue Aug 23 15:39:56 2016 +1000

    Stage C feedback

commit 6115dc87a02df0347f3742dd27c22bbadd48e3a8
Merge: ef0ca35 09036fb
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Aug 23 13:05:20 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 09036fb356d89f1def2ae47c1a5902600fe203df
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Aug 23 11:15:50 2016 +1000

    Updated origignality

commit c9a9b43cc60ec40460e10d31da11407eb941af12
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Aug 23 11:15:10 2016 +1000

    Added contribution

commit a11a6e473c910693cb5340b0dea24fec39f0d40a
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Mon Aug 22 22:59:50 2016 +1000

    Cleanup

commit feba184e567e9884ccea917445ed8926345676f8
Author: manalmohania <u6019071@anu.edu.au>
Date:   Mon Aug 22 22:40:22 2016 +1000

    Checked all array bounds and passed all tests!

commit 1ccbccd8c0107cafe9d3fc99d690e32340d2193c
Merge: 16fb115 d91a7e5
Author: manalmohania <u6019071@anu.edu.au>
Date:   Mon Aug 22 18:16:18 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 16fb1155198863d8ec4992a5180790e835c8d57a
Merge: f90369d a72ebc5
Author: manalmohania <u6019071@anu.edu.au>
Date:   Mon Aug 22 18:15:13 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

    # Conflicts:
    #       src/comp1110/ass2/StratoGame.java

commit d91a7e5cec566ae272edcf1fbf9f14fb07ed9d56
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Mon Aug 22 14:59:39 2016 +1000

    Task 6: Added outOfBounds detection

commit f90369d0b688748abfa40587f35d9e4402fbe9e4
Author: manalmohania <u6019071@anu.edu.au>
Date:   Mon Aug 22 14:51:42 2016 +1000

    Some changes to aadjacecncy

commit a72ebc50ad8b0f3200037bfd5aafe0fdc6be47a4
Author: Joseph Meltzer <u6060661@anu.edu.au>
Date:   Mon Aug 22 14:45:14 2016 +1000

    Task 6: Fixed Straddle

commit 93d697564e88ed5f50879c0cd4b5fe795def87ea
Author: manalmohania <u6019071@anu.edu.au>
Date:   Mon Aug 22 14:02:00 2016 +1000

    Passed testBad of Task 6
    testGood is still failing

commit ef0ca35d5a9ed5f2781b6c7865b0c7109cdfb7a7
Merge: 305bafe fbb9a36
Author: u5807060 <u5807060@anu.edu.au>
Date:   Mon Aug 22 13:51:04 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 68d471f505c68183b229ee8ac9fd8add51ddb4b8
Merge: fbb9a36 e81747c
Author: manalmohania <u6019071@anu.edu.au>
Date:   Mon Aug 22 13:38:14 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit fbb9a3684b29f189aa5bd8fc3f39080cea41408e
Merge: 77ec407 4e8d0e7
Author: manalmohania <u6019071@anu.edu.au>
Date:   Mon Aug 22 13:28:34 2016 +1000

    Fixed 4

commit 305bafe8854b5f8f19d4132071b80ee0bafcf2dd
Merge: 4e8d0e7 e81747c
Author: u5807060 <u5807060@anu.edu.au>
Date:   Mon Aug 22 13:26:36 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit 77ec407b6f0335ea315d5c948f20fc90d0b5b584
Author: manalmohania <u6019071@anu.edu.au>
Date:   Mon Aug 22 13:04:12 2016 +1000

    I don't what's been commited -- not going to push it though

commit 4e8d0e745bae75e4764e8cb34751e6fe203d6046
Author: u5807060 <u5807060@anu.edu.au>
Date:   Mon Aug 22 13:03:48 2016 +1000

    some edits

commit 1297fca86186e3e17bbd3c0b7ecb46241648a392
Author: u5807060 <u5807060@anu.edu.au>
Date:   Mon Aug 22 12:58:56 2016 +1000

    redid task 3

commit a3ce6d4b9306f177a4073a595b4ac8a71f8248b7
Author: u5807060 <u5807060@anu.edu.au>
Date:   Mon Aug 22 12:54:23 2016 +1000

    finished task 5

commit 751d13f18ee23b8e630c37356705feb74fc8d557
Merge: d3ccd6e 56cefdf
Author: u5807060 <u5807060@anu.edu.au>
Date:   Mon Aug 22 12:34:43 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit d3ccd6e51c94c58e2aed5521dba259e8c8df4fd0
Author: u5807060 <u5807060@anu.edu.au>
Date:   Mon Aug 22 12:34:32 2016 +1000

    Tiles always display at (0,0)

commit e81747c45d70e73a483472df2fa68dc262411ecd
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Mon Aug 22 12:26:29 2016 +1000

    Fixed tests to ensure that stacked placements straddle at least two tiles below, in accordance with the rules.

commit a321f517592195145c41248eef4c6cfb0a708efe
Merge: 56cefdf a44528d
Author: manalmohania <u6019071@anu.edu.au>
Date:   Mon Aug 22 12:17:15 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit 56cefdf6c9c6a59dc8cf02920bf38376f7a7da52
Merge: 511718c fdb307b
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Mon Aug 22 13:44:26 2016 +1200

    Merge remote-tracking branch 'origin/master'

commit 511718c2ca12e7598efcc6f2aaacd9be4e6b4c6b
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Mon Aug 22 13:43:50 2016 +1200

    Task 6: Created method tileStraddle() for checking straddling condition. Also fixed bugs in areColoursAlright()

commit fdb307b35827a196bc44292bfdcb5b724ceb71e6
Merge: 077d289 cc9433b
Author: u5807060 <u5807060@anu.edu.au>
Date:   Mon Aug 22 09:39:23 2016 +1000

    Merge remote-tracking branch 'origin/master'

    # Conflicts:
    #       src/comp1110/ass2/gui/Viewer.java

commit 077d2891e2ffdb2b53102606c153718337f9c5d2
Author: u5807060 <u5807060@anu.edu.au>
Date:   Mon Aug 22 09:37:18 2016 +1000

    Using GridPane for the placement. The tiles aren't showing up in the right row and column.

commit a44528db6571072b8e637ac9af7f9e492f80e0d1
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Mon Aug 22 07:50:38 2016 +1000

    Added tests for tasks 3, 4, and 6.

commit cc9433b50b3ea2c54714c1b99427f13affefaf30
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Mon Aug 22 00:39:33 2016 +1200

    Task 6: Fixed conditions in isColoursAlright()

commit 80b5428ad51edb4a7b0830c8f02987fa4567a370
Merge: 1aafb0c 3fd0d31
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Mon Aug 22 00:06:49 2016 +1200

    Merge remote-tracking branch 'origin/master'

commit 1aafb0c55a3fdcf65a64e7066c411b61683b7430
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Mon Aug 22 00:05:51 2016 +1200

    Task 6: implemented areColoursAlright()

commit 3fd0d31621e551d6ddc590a31d4e3df6398fc8b4
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Aug 21 22:04:39 2016 +1000

    Corrected default tile position

commit ebee726c21eb3deab385b696362bdf0d01f9f995
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Aug 21 20:50:33 2016 +1000

    Added comment to viewer.java

commit 740807d231d7cd59d3abf51b89982777822916d9
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sun Aug 21 16:45:42 2016 +1000

    will soon try getting rid of hbox on tiles

commit e25b3c77a96430c5c562751e9f888ae0e4827f87
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sun Aug 21 16:10:51 2016 +1000

    It reads the tile placement location correctly now.

commit 81300228ac20f33058e102a30b377e24a3bc4028
Merge: 35b147c b0fefd2
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sun Aug 21 15:58:27 2016 +1000

    Merge remote-tracking branch 'origin/master'

    # Conflicts:
    #       src/comp1110/ass2/gui/Viewer.java

commit 35b147c80e066c18d03857161e2429dab55d79c8
Author: u5807060 <u5807060@anu.edu.au>
Date:   Sun Aug 21 15:57:22 2016 +1000

    Fixed isPlacementWellFormed. Have a loop displaying many images in different locations. Still need to resize images, rotate them, and place them in the right location.

commit b0fefd2722e1b383fa4abfac2eae80987f1cc1a6
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Aug 21 15:32:20 2016 +1000

    Corrected another comment

commit 91c992dbe6b780b814f663e79884f93078d2a3c1
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Aug 21 14:35:02 2016 +1000

    Corrected amibiguous comment

commit b32b8646c6866952ebc2b07a551a8719198bebd2
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Aug 21 11:11:57 2016 +1000

    Enhanced pieces.java and added lots of comments.

commit c819b9e9625149e87e3044e7291faa431b783a63
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Aug 21 10:08:59 2016 +1000

    Fixed logic for adjacency

commit a7f6e7abd626c707c2b2e0090043f83051f89bb8
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sun Aug 21 00:27:43 2016 +1000

    Representation for tiles -- going off to sleep now

commit d71790ee3be1898cf91a00b184bb0e4aa2fc535d
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Aug 20 23:47:28 2016 +1000

    Completed the adjacency check (I think)

commit f1faa7556b73f9e8d133e76f261b91e0f0684291
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Aug 20 22:44:08 2016 +1000

    Progress to task 6

commit 457410bfa43d2bc865d6aca8f39c1b05628ff359
Author: manalmohania <u6019071@anu.edu.au>
Date:   Sat Aug 20 21:14:32 2016 +1000

    Begun task 6

commit 42016d495c071cb6bffaeeb9731b98519b3090f8
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 19 21:34:44 2016 +1000

    Updated comments

commit 5162d1e3bc5ed036fedc664d790181e9c8829c00
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 19 18:45:43 2016 +1000

    Added a few comments

commit 2f21874a70e2f5aee602d1ffd62864e209c1b0df
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 19 18:34:04 2016 +1000

    Fixed bug -- screen clears after each new placement

commit 15072141a3d2b7fef36c963059fd157049379eb8
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 19 13:08:45 2016 +1000

    Added spaces

commit 3c0954c1cbb62919b3a17a2d4cffd78011ad89a8
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 19 11:27:37 2016 +1000

    Got rid of gittest. The tile corresponding to the third character is printed. Does NOT check for errors

commit 358450fb032ef9c073afe996aa60151d2a9b0055
Merge: 972b880 23a41d4
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 19 10:27:02 2016 +1000

    Merge remote-tracking branch 'origin/master'

    # Conflicts:
    #       src/gittest/Main.java

commit 972b88086fe4ff901563d60275257ff7f31317e4
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 19 10:26:36 2016 +1000

    A positive start to task 5 - printing the tile U

commit 23a41d42f15eaedeadbee28b0c3b01dc6a957549
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Aug 19 10:16:40 2016 +1000

    pulled from upstream

commit b1f6fd829d3eeaccbccf85b2e943334c39722111
Merge: 0b7def5 953f7be
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Aug 19 10:15:12 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 0b7def563edd58ff061b73c4d69691f9cb2f3b04
Merge: 6483114 dcc4ca5
Author: u5807060 <u5807060@anu.edu.au>
Date:   Fri Aug 19 10:13:42 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit 953f7beea686a69d1d9c60633b465fc171620bf6
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Aug 18 14:33:58 2016 +1000

    Erroneous build to task 5

commit 4d9a1af37ad68532af15d82509ef6d857809d3b5
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Aug 18 14:24:55 2016 +1000

    Small start to task 5

commit 1b41966792fc8b9f215c67f3b027ea4d8620f972
Merge: cafdd53 464859c
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Aug 18 13:56:27 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit cafdd53f0aab4873778ba7b4c036a55b0af78287
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Aug 18 13:56:18 2016 +1000

    Completed Task 4

commit 464859c7effe412fe585bbe7dcd88ee455ffe78a
Merge: 0182e56 6483114
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Aug 18 13:55:32 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 0182e566aa52ef3900d5a247ce5c22e8c09c513a
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Aug 18 13:55:11 2016 +1000

    finished task 3

commit 648311493ac91f84aa93a8b281045f6e3cad4a1a
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Aug 16 14:13:11 2016 +1000

    merged main

commit d48665ae3158c466b64a2ae3ab8db968ef6d2f4e
Merge: 36616d3 abd0803
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Aug 16 14:04:21 2016 +1000

    Merge remote-tracking branch 'origin/master'

    # Conflicts:
    #       src/gittest/Main.java

commit 36616d3b4a622a168331bb9aa272121de7bc1b4d
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Aug 16 14:03:03 2016 +1000

    Corrected the line in main

commit abd0803b6d0cdb418db97443108f446ef253d548
Merge: 19ff402 3e3e109
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Tue Aug 16 14:01:06 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 3e3e1091b27ef98f337014e541709196b11d881e
Author: Alice <u5807060@anu.edu.au>
Date:   Tue Aug 16 14:00:24 2016 +1000

    alice step 8, 9

commit d0b8babc43c91e619e9e0912cdb9ac99bbbd4164
Author: CIndy <u6019071@anu.edu.au>
Date:   Tue Aug 16 13:58:45 2016 +1000

    Added line to main

commit 19ff402ba0ecf3012de4ef31d0ca430b5bd026e6
Author: Bob <u6060661@anu.edu.au>
Date:   Tue Aug 16 13:58:11 2016 +1000

    Bob's Step 8

commit 9386df97011a04fd197208ade31fb28a5c23e145
Merge: 99db4ff 6bc09b4
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Tue Aug 16 13:56:50 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 6bc09b459556c74e587674ea511dd126836d2e0b
Author: Alice <u5807060@anu.edu.au>
Date:   Tue Aug 16 13:56:30 2016 +1000

    alice main

commit 0e57b89439a2f46715e99ea6a98cfd92d620f672
Author: Alice <u5807060@anu.edu.au>
Date:   Tue Aug 16 13:50:01 2016 +1000

    alice step 9

commit 99db4ffa9580cff7984e0d010d2e90b9efd194d7
Author: Bob <u6060661@anu.edu.au>
Date:   Tue Aug 16 13:42:10 2016 +1000

    Bob's Step 7, again.

commit 9d67e2db465f93055da2eb497cfdd9dfcb38e948
Author: Bob <u6060661@anu.edu.au>
Date:   Tue Aug 16 13:41:46 2016 +1000

    Bob's Step 7

commit 983ac9c6254b291e0111f77824ce631d8cb78b36
Merge: e972bb3 979febc
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Tue Aug 16 13:39:25 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 979febc277cdc35575c414e5bda23d073d568122
Merge: 7377571 b8154ee
Author: CIndy <u6019071@anu.edu.au>
Date:   Tue Aug 16 13:39:00 2016 +1000

    Added C

commit 7377571076cb0add36cfc8706c3a4c666665c709
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Aug 16 13:34:55 2016 +1000

    Useless commit

commit e972bb341f9c8ae4a884d4c22406079dcad2bb01
Author: Bob <u6060661@anu.edu.au>
Date:   Tue Aug 16 13:30:25 2016 +1000

    Bob's Changes

commit b8154ee633072d673cf1aff0f0b59f549db402b5
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Aug 16 13:26:55 2016 +1000

    originality statement

commit f21169e2de16a431101af857c82923b14671972b
Merge: 38829d3 8659230
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Aug 16 13:25:08 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 38829d343e0ff0856fc24ba10e55e2b53dd43c11
Author: Alice <u5807060@anu.edu.au>
Date:   Tue Aug 16 13:24:41 2016 +1000

    alice stuff

commit 86592306d5d9d88703e458fe328ecd8f9a79bc50
Author: manalmohania <u6019071@anu.edu.au>
Date:   Mon Aug 15 12:16:02 2016 +1000

    Added png version of UML diagram

commit 9be17eb080e8aab16cb1e3fac9b0de093c30da29
Author: manalmohania <u6019071@anu.edu.au>
Date:   Fri Aug 12 13:12:31 2016 +1000

    Added UML diagram

commit 92489379fd74d688d21a4f96a44723a4fd4691c8
Merge: 6376497 a480f0a
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Aug 11 14:37:02 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 6376497a49c60d49e37d088598359ad6206abe70
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Aug 11 14:36:54 2016 +1000

    Changed Project Language Level

commit a480f0a4f73dec0f45ba43aa66f65abc6063bbf5
Merge: a07a550 b4dc311
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Aug 11 14:36:28 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit a07a550dc9027628e9e415c12f7ef9214bd8cdc1
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Aug 11 14:36:08 2016 +1000

    Added BoardState as required arguements for makePlacement and isPlacementValid. Gave the two players class variables that tell us if they're the RED or GREEN player (the players need different constructors to make their list of tiles).

commit b4dc311cc16d232351ddda085261577304063a5b
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Aug 11 14:29:54 2016 +1000

    Deleted extra files

commit 4fb0f32a122e19de9a1d40fc5bd20a7402144bc0
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Aug 11 14:24:57 2016 +1000

    updated originality statement. most fiels still outside the gui folder as we want.

commit 288a74141cd6195318f134f7eec6f37eddcff73a
Merge: 7fed5b2 a6db7f9
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Aug 11 14:21:59 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 7fed5b2132ee9d300d8ba5786ba51839e4c88160
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Aug 11 14:21:53 2016 +1000

    Added contibution.md

commit a6db7f9622733ce36192fd344cd4287e38f6fb5f
Merge: 9c58258 c8c57fc
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Aug 11 14:19:14 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 9c58258bc509c939f07c6d9bd671060042448198
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Aug 11 14:18:58 2016 +1000

    some comments, and putting files outside GUI

commit c8c57fcf92d9131ee47e6775f32bf2a8491de136
Merge: dfc65f3 79675bd
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Thu Aug 11 16:16:26 2016 +1200

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 79675bd0a1b0ad33fb97277e3f56712c381478ce
Merge: 65f69b8 33f190e
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Aug 11 14:16:04 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 65f69b83512279032f3b08737c74ba1fb20cc5a0
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Aug 11 14:15:54 2016 +1000

    Added the Player classes

commit dfc65f3b85ed59db3e7703cb8c9383333b932f11
Merge: 1fe8a02 33f190e
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Thu Aug 11 16:15:52 2016 +1200

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 1fe8a02e37106ff99c7b2b76587c49479d4cdcdb
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Thu Aug 11 16:15:45 2016 +1200

    Task 1: Added AI class, and import to gui.Board

commit 33f190e67a25fe908ea402421a5908b5088720cb
Merge: f544e16 5d4e02c
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Aug 11 14:15:44 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit f544e16ac9a1c241739123911329e6246e6c4b5d
Author: u5807060 <u5807060@anu.edu.au>
Date:   Thu Aug 11 14:15:31 2016 +1000

    Tiles, Colour, BoardState

commit 5d4e02c536ad39db5e4b9edfbd3f8cbae899ca94
Merge: 2bbbe34 85a9029
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Thu Aug 11 16:11:30 2016 +1200

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 85a90297fccf2277378a9ae47a8bb4b7619aa58f
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Aug 11 14:10:32 2016 +1000

    Add Pieces enum

commit 2bbbe349ab758303732de763bb07a3866bbd5c50
Author: Joseph Dylan Meltzer <u6060661@anu.edu.au>
Date:   Thu Aug 11 16:10:07 2016 +1200

    Task 1: Added GUI Setup class, and import to Viewer

commit 5d7bf8fb574a9991fa4ae03bf98ac690972c741f
Author: manalmohania <u6019071@anu.edu.au>
Date:   Thu Aug 11 14:08:05 2016 +1000

    Add Pieces enum

commit dcc4ca54fb01879ea421ac0f7f55951609504cc3
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Wed Aug 10 06:55:32 2016 +1000

    Update README.md

commit 476609eebbb806516685c8403b4025de2b80e2d4
Merge: 4eec071 aab3d21
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Aug 9 14:57:21 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6019071/comp1140-ass2-tue13p

commit 4eec071dd5ab6becf1de61ae3c46555c8954e543
Author: manalmohania <u6019071@anu.edu.au>
Date:   Tue Aug 9 14:56:57 2016 +1000

    Updated grouo info

commit aab3d21f8209938353a4a2e18740db9260341add
Author: u5807060 <u5807060@anu.edu.au>
Date:   Tue Aug 9 14:29:11 2016 +1000

    upstream

commit 88186a253ff9452256a764b0e1d4698817611c4e
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Tue Aug 9 12:47:52 2016 +1000

    Initial import
```
## Changes
```
Only in comp1140-ass2/.idea: artifacts
diff -ru -x .git ../master/comp1140-ass2/.idea/comp1140-ass2.iml comp1140-ass2/.idea/comp1140-ass2.iml
--- ../master/comp1140-ass2/.idea/comp1140-ass2.iml	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/.idea/comp1140-ass2.iml	2016-10-15 14:00:01.000000000 +1100
@@ -9,5 +9,15 @@
     </content>
     <orderEntry type="inheritedJdk" />
     <orderEntry type="sourceFolder" forTests="false" />
+    <orderEntry type="module-library" scope="TEST">
+      <library name="JUnit4">
+        <CLASSES>
+          <root url="jar://$APPLICATION_HOME_DIR$/lib/junit-4.12.jar!/" />
+          <root url="jar://$APPLICATION_HOME_DIR$/lib/hamcrest-core-1.3.jar!/" />
+        </CLASSES>
+        <JAVADOC />
+        <SOURCES />
+      </library>
+    </orderEntry>
   </component>
 </module>
\ No newline at end of file
Only in comp1140-ass2/.idea: dictionaries
diff -ru -x .git ../master/comp1140-ass2/.idea/misc.xml comp1140-ass2/.idea/misc.xml
--- ../master/comp1140-ass2/.idea/misc.xml	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/.idea/misc.xml	2016-10-15 14:00:01.000000000 +1100
@@ -3,13 +3,6 @@
   <component name="EntryPointsManager">
     <entry_points version="2.0" />
   </component>
-  <component name="MavenImportPreferences">
-    <option name="generalSettings">
-      <MavenGeneralSettings>
-        <option name="mavenHome" value="Bundled (Maven 3)" />
-      </MavenGeneralSettings>
-    </option>
-  </component>
   <component name="ProjectLevelVcsManager" settingsEditedManually="false">
     <OptionsSetting value="true" id="Add" />
     <OptionsSetting value="true" id="Remove" />
@@ -20,7 +13,7 @@
     <ConfirmationsSetting value="0" id="Add" />
     <ConfirmationsSetting value="0" id="Remove" />
   </component>
-  <component name="ProjectRootManager" version="2" languageLevel="JDK_1_8" default="true" assert-keyword="true" jdk-15="true" project-jdk-name="1.8" project-jdk-type="JavaSDK">
+  <component name="ProjectRootManager" version="2" languageLevel="JDK_1_8" default="false" assert-keyword="true" jdk-15="true" project-jdk-name="1.8" project-jdk-type="JavaSDK">
     <output url="file://$PROJECT_DIR$/out" />
   </component>
 </project>
\ No newline at end of file
diff -ru -x .git ../master/comp1140-ass2/.idea/modules.xml comp1140-ass2/.idea/modules.xml
--- ../master/comp1140-ass2/.idea/modules.xml	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/.idea/modules.xml	2016-10-15 14:00:01.000000000 +1100
@@ -5,4 +5,4 @@
       <module fileurl="file://$PROJECT_DIR$/.idea/comp1140-ass2.iml" filepath="$PROJECT_DIR$/.idea/comp1140-ass2.iml" />
     </modules>
   </component>
-</project>
+</project>
\ No newline at end of file
Only in comp1140-ass2/admin: A-Info.md
diff -ru -x .git ../master/comp1140-ass2/admin/B-contribution.md comp1140-ass2/admin/B-contribution.md
--- ../master/comp1140-ass2/admin/B-contribution.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/B-contribution.md	2016-10-15 14:00:01.000000000 +1100
@@ -1,7 +1,7 @@
 We declare that the work toward our submission of Stage B was distributed among the group members as follows:

-* u2345678 30
-* u3456789 40
-* u0234567 30
+* u6019071 100/3
+* u6060661 100/3
+* u5807060 100/3

-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0234567)
+Signed: Manal Mohania (u6019071), Joseph Meltzer (u6060661), and Zhixian Wu (u5807060)
diff -ru -x .git ../master/comp1140-ass2/admin/B-originality.md comp1140-ass2/admin/B-originality.md
--- ../master/comp1140-ass2/admin/B-originality.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/B-originality.md	2016-10-15 14:00:01.000000000 +1100
@@ -1,9 +1,5 @@
 We declare that the work we have submitted for Stage B of this assignment and all stages before it is entirely our own work, with the following documented exceptions:

-* The idea of using <...> to make the game run faster came from a discussion with <...> (noted in source code comments)
+* No exceptions

-* The code in class <...> is based on a solution we found when researching the problem (URL provided in source code comments)
-
-*  ....
-
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+Signed: Manal Mohania (u6019071), Joseph Meltzer (u6060661), and Zhixian Wu (u5807060)
diff -ru -x .git ../master/comp1140-ass2/admin/C-contribution.md comp1140-ass2/admin/C-contribution.md
--- ../master/comp1140-ass2/admin/C-contribution.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/C-contribution.md	2016-10-15 14:00:01.000000000 +1100
@@ -1,7 +1,7 @@
 We declare that the work toward our submission of Stage C was distributed among the group members as follows:

-* u2345678 30
-* u3456789 40
-* u0234567 30
+* u6019071 40
+* u6060661 30
+* u5807060 30

-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0234567)
+Signed: Manal Mohania (u6019071), Joseph Meltzer (u6060661), and Zhixian Wu (u5807060)
Only in comp1140-ass2/admin: C-feedback.md
diff -ru -x .git ../master/comp1140-ass2/admin/C-originality.md comp1140-ass2/admin/C-originality.md
--- ../master/comp1140-ass2/admin/C-originality.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/C-originality.md	2016-10-15 14:00:01.000000000 +1100
@@ -1,9 +1,4 @@
-We declare that the work we have submitted for Stage C of this assignment and all stages before it is entirely our own work, with the following documented exceptions:
+We declare that the work we have submitted for Stage C of this assignment and all stages before it is entirely our own work

-* The idea of using <...> to make the game run faster came from a discussion with <...> (noted in source code comments)

-* The code in class <...> is based on a solution we found when researching the problem (URL provided in source code comments)
-
-*  ....
-
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+Signed: Manal Mohania (u6019071), Joseph Meltzer (u6060661), and Zhixian Wu (u5807060)
Only in ../master/comp1140-ass2/admin: D-originality-u1234567.md
Only in comp1140-ass2/admin: D-originality-u5807060
Only in comp1140-ass2/admin: D-originality-u6019071.md
Only in comp1140-ass2/admin: D-originality-u6060661.md
Only in ../master/comp1140-ass2/admin: E-originality-u1234567.md
Only in comp1140-ass2/admin: E-originality-u5807060.md
Only in comp1140-ass2/admin: E-originality-u6019071.md
Only in comp1140-ass2/admin: E-originality-u6060661.md
diff -ru -x .git ../master/comp1140-ass2/admin/F-contribution.md comp1140-ass2/admin/F-contribution.md
--- ../master/comp1140-ass2/admin/F-contribution.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/F-contribution.md	2016-10-15 14:00:01.000000000 +1100
@@ -1,8 +1,8 @@
 We declare that the work toward our submission of Stage F was distributed among the group members as follows:

-* u2345678 30
-* u3456789 40
-* u0123456 30
+* u6019071 100/3
+* u6060661 100/3
+* u5807060 100/3

-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+Signed: Manal Mohania (u6019071), Joseph Meltzer (u6060661), and Zhixian Wu (u5807060)

diff -ru -x .git ../master/comp1140-ass2/admin/F-originality.md comp1140-ass2/admin/F-originality.md
--- ../master/comp1140-ass2/admin/F-originality.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/F-originality.md	2016-10-15 14:00:01.000000000 +1100
@@ -1,9 +1,5 @@
-We declare that the work we have submitted for Stage F of this assignment and all stages before it is entirely our own work, with the following documented exceptions:
+We declare that the work we have submitted for Stage F of this assignment is entirely our own work

-* The idea of using <...> to make the game run faster came from a discussion with <...> (noted in source code comments)

-* The code in class <...> is based on a solution we found when researching the problem (URL provided in source code comments)
+Signed: Manal Mohania (u6019071), Joseph Meltzer (u6060661), and Zhixian Wu (u5807060)

-* ....
-
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
Only in ../master/comp1140-ass2/admin: G-best-u1234567.md
Only in comp1140-ass2/admin: G-best-u5807060.md
Only in comp1140-ass2/admin: G-best-u6019071.md
Only in comp1140-ass2/admin: G-best-u6060661.md
diff -ru -x .git ../master/comp1140-ass2/admin/G-contribution.md comp1140-ass2/admin/G-contribution.md
--- ../master/comp1140-ass2/admin/G-contribution.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/G-contribution.md	2016-10-15 14:00:01.000000000 +1100
@@ -1,8 +1,7 @@
 We declare that the work toward our submission of Stage G was distributed among the group members as follows:

-* u2345678 30
-* u3456789 40
-* u0123456 30
-
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+* u6019071 100/3
+* u6060661 100/3
+* u5807060 100/3

+Signed: Manal Mohania (u6019071), Joseph Meltzer (u6060661), and Zhixian Wu (u5807060)
diff -ru -x .git ../master/comp1140-ass2/admin/G-features.md comp1140-ass2/admin/G-features.md
--- ../master/comp1140-ass2/admin/G-features.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/G-features.md	2016-10-15 14:00:01.000000000 +1100
@@ -9,6 +9,14 @@
  - Heights of regions indicated
  - Running scores
  - Basic computer opponent
- - Advanced computer opponent
+ - Advanced computer opponent (uses a probabilistic approach beyond two levels)

 additional features...
+- Previews of the placement to be made when you hover over the board, with the image desaturated when it is not valid
+- A sound emitted whenever a placement is made, with a mute button
+- The option to watch two AIs play against each other.
+- An AI that can cheat by looking deeper than the top tiles of the deck
+- A button that creates a pop-up of the instructions
+- The option to go back to the main menu and start a new game
+- Number of tiles left for each player is indicated
+- When hovering over the board, a human player can rotate their tile by scrolling
\ No newline at end of file
diff -ru -x .git ../master/comp1140-ass2/admin/G-originality.md comp1140-ass2/admin/G-originality.md
--- ../master/comp1140-ass2/admin/G-originality.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/G-originality.md	2016-10-15 14:00:01.000000000 +1100
@@ -1,9 +1,17 @@
 We declare that the work we have submitted for Stage G of this assignment and all stages before it is entirely our own work, with the following documented exceptions:

-* The idea of using <...> to make the game run faster came from a discussion with <...> (noted in source code comments)
+* The CSS code for styling buttons was heavily adapted from <http://fxexperience.com/2011/12/styling-fx-buttons-with-css/>

-* The code in class <...> is based on a solution we found when researching the problem (URL provided in source code comments)
+* The code for all variations of 'addPane' in class Board if heavily adapted from:
+* <http://stackoverflow.com/questions/31095954/how-to-get-gridpane-row-and-column-ids-on-mouse-entered-in-each-cell-of-grid-in>

-* ....
+* The image of the mute 'button' was from <https://pixabay.com/en/icon-loudspeaker-speaker-horn-1628258/>

-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+* The sound emitted whenever a placement is made was from
+* <http://www.sounds.beachware.com/2illionzayp3may/jspjrz/SWITCH.mp3>
+
+* Some of the instructions were copied from <https://boardgamegeek.com/boardgame/125022/stratopolis>
+
+* The Stratopolis logo on the first game screen is a variation we made of the official logo.
+
+Signed: Manal Mohania (u6019071), Joseph Meltzer (u6060661), and Zhixian Wu (u5807060)
Binary files ../master/comp1140-ass2/assets/A.png and comp1140-ass2/assets/A.png differ
Only in comp1140-ass2: diagram.png
Only in comp1140-ass2: feedback.md
Only in comp1140-ass2: game.jar
Only in comp1140-ass2: presentation.pdf
Only in comp1140-ass2/src: META-INF
Only in comp1140-ass2/src/comp1110/ass2: AI.java
Only in comp1140-ass2/src/comp1110/ass2: Colour.java
Only in comp1140-ass2/src/comp1110/ass2: Difficulty.java
Only in comp1140-ass2/src/comp1110/ass2: GameState.java
Only in comp1140-ass2/src/comp1110/ass2: Pieces.java
Only in comp1140-ass2/src/comp1110/ass2: Player.java
Only in comp1140-ass2/src/comp1110/ass2: PlayerG.java
Only in comp1140-ass2/src/comp1110/ass2: PlayerR.java
Only in comp1140-ass2/src/comp1110/ass2: Scoring.java
diff -ru -x .git ../master/comp1140-ass2/src/comp1110/ass2/StratoGame.java comp1140-ass2/src/comp1110/ass2/StratoGame.java
--- ../master/comp1140-ass2/src/comp1110/ass2/StratoGame.java	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/src/comp1110/ass2/StratoGame.java	2016-10-15 14:00:01.000000000 +1100
@@ -1,5 +1,15 @@
 package comp1110.ass2;

+import static comp1110.ass2.AI.alphabeta;
+import static comp1110.ass2.AI.alphabetaCheat;
+import static comp1110.ass2.AI.validTiles;
+import static comp1110.ass2.Colour.*;
+import static comp1110.ass2.Pieces.getColours;
+import static comp1110.ass2.Player.MAX_TILES;
+import static comp1110.ass2.Scoring.getScore;
+import static comp1110.ass2.Scoring.getWinner;
+import static java.lang.StrictMath.min;
+
 /**
  * This class provides the text interface for the Strato Game
  *
@@ -8,20 +18,12 @@
  */
 public class StratoGame {

-    /**
-     * Determine whether a tile placement is well-formed according to the following:
-     * - it consists of exactly four characters
-     * - the first character is in the range A .. Z
-     * - the second character is in the range A .. Z
-     * - the third character is in the range A .. U
-     * - the fourth character is in the range A .. D
-     *
-     * @param tilePlacement A string describing a tile placement
-     * @return True if the tile placement is well-formed
-     */
     static boolean isTilePlacementWellFormed(String tilePlacement) {
-        // FIXME Task 3: determine whether a tile placement is well-formed
-        return false;
+        return tilePlacement.length() == 4 && tilePlacement.charAt(0) >= 'A' &&
+                tilePlacement.charAt(0) <= 'Z' && tilePlacement.charAt(1) >= 'A' &&
+                tilePlacement.charAt(1) <= 'Z' && tilePlacement.charAt(2) >= 'A' &&
+                tilePlacement.charAt(2) <= 'U' && tilePlacement.charAt(3) >= 'A' &&
+                tilePlacement.charAt(3) <= 'D';
     }

     /**
@@ -32,16 +34,33 @@
      *  - the second tile placement (if any) is for a green tile
      *  - remaining tile placements alternate between red and green
      *  - no tile appears more than twice in the placement
+     *  Method by Manal Mohania and Joseph Meltzer
      *
-     * @param placement A string describing a placement of one or more tiles
-     * @return True if the placement is well-formed
+     * @param placement     A string describing a placement of one or more tiles
+     * @return              True if the placement is well-formed
      */
     static boolean isPlacementWellFormed(String placement) {
-        // FIXME Task 4: determine whether a placement is well-formed
-        String foo;
-        return false;
+        if (placement == null) return false;
+        int len = placement.length();
+        int numPieces = len/4;
+        int[] counter = new int[20];
+        Boolean c1 = (len % 4 == 0) && (numPieces >=1) && (numPieces <= 41);
+        if (!c1) return false;
+        if (! placement.substring(0,4).equals("MMUA")) return false;
+        for (int i=2; i<len; i+=4) {
+            if (i%4==2 && !(isTilePlacementWellFormed(placement.substring(i-2,i+2)))) return false;
+            if (i%8==6 && !(placement.charAt(i) >= 'K' && placement.charAt(i) <= 'T')) return false;
+            if (i%8==2 && i>9 && !(placement.charAt(i) >= 'A' && placement.charAt(i) <= 'J')) return false;
+            if (i%4==2 && i>5) {
+                int idx = placement.charAt(i) - 'A';
+                counter[idx]++;
+                if (counter[idx]>2) return false;
+            }
+        }
+        return true;
     }

+
     /**
      * Determine whether a placement is valid.  To be valid, the placement must be well-formed
      * and each tile placement must follow the game's placement rules.
@@ -49,36 +68,459 @@
      * @param placement A placement string
      * @return True if the placement is valid
      */
-    static boolean isPlacementValid(String placement) {
-        // FIXME Task 6: determine whether a placement is valid
-        return false;
+    public static boolean isPlacementValid(String placement) {
+        return isPlacementWellFormed(placement) && isPlacementAdjacent(placement) && straddleAndColours(placement);
+    }
+
+    /**
+     * Convert a placement string to a 26x26 array of colours
+     * Function written by Manal Mohania
+     * @param placement: A _valid_ placement string
+     * @return a 26 x 26 array of colours
+     */
+    static Colour[][] colourArray(String placement){
+        Colour[][] coverage = new Colour[26][26];
+        coverage[12][12] = RED;
+        coverage[12][13] = GREEN;
+        
+        for (int i = 4; i < placement.length(); i += 4){
+            /*jump to required position*/
+            int col = placement.charAt(i) - 'A';
+            int row = placement.charAt(i + 1) - 'A';
+            char piece = placement.charAt(i + 2);
+            char orientation = placement.charAt(i + 3);
+            /*based on the orientation change the appropriate colours*/
+
+            coverage[col][row] = Pieces.valueOf(""+piece).colours[0];
+
+            if (orientation == 'A'){
+                coverage[col + 1][row] = Pieces.valueOf(""+piece).colours[1];
+                coverage[col][row + 1] = Pieces.valueOf(""+piece).colours[2];
+            }
+            else if (orientation == 'B'){
+                coverage[col][row + 1] = Pieces.valueOf(""+piece).colours[1];
+                coverage[col - 1][row] = Pieces.valueOf(""+piece).colours[2];
+            }
+            else if (orientation == 'C'){
+                coverage[col - 1][row] = Pieces.valueOf(""+piece).colours[1];
+                coverage[col][row - 1] = Pieces.valueOf(""+piece).colours[2];
+            }
+            else if (orientation == 'D'){
+                coverage[col][row - 1] = Pieces.valueOf(""+piece).colours[1];
+                coverage[col + 1][row] = Pieces.valueOf(""+piece).colours[2];
+            }
+            else
+                System.out.println("colourArray: should not reach here");
+        }
+        return coverage;
+    }
+
+    /**
+     * Convert a placement string to a 26x26 array of heights
+     * Function written by Manal Mohania
+     * @param placement: A _valid_ placement string
+     * @return a 26 x 26 array of heights
+     */
+    public static int[][] heightArray(String placement){
+        int[][] coverage = new int[26][26];
+        coverage[12][12] = 1;
+        coverage[12][13] = 1;
+
+        for (int i = 4; i < placement.length(); i += 4){
+            int col = placement.charAt(i) - 'A';
+            int row = placement.charAt(i + 1) - 'A';
+            coverage[col][row]++;
+            if (placement.charAt(i+3) == 'A'){
+                coverage[1 + col][row]++;
+                coverage[col][1 + row]++;
+            }
+            else if (placement.charAt(i + 3) == 'B'){
+                coverage[-1 + col][row]++;
+                coverage[col][1 + row]++;
+            }
+            else if (placement.charAt(i + 3) == 'C'){
+                coverage[-1 + col][row]++;
+                coverage[col][-1 + row]++;
+            }
+            else if (placement.charAt(i + 3) == 'D'){
+                coverage[1 + col][row]++;
+                coverage[col][-1 + row]++;
+            }
+        }
+        return coverage;
+    }
+
+    /**
+     * This method checks
+     *
+     * 1. if each of the tiles in the placement string share at least one side in common with another tile.
+     * 2. if the tile lies on top of another, it ensures that the tile os not dangling, and the squares it rests upon have the same heights.
+     * 3. that no part of any tile falls of the board
+     *
+     * @param placement: the placement string
+     * @return true iff the above three conditions are satisfied
+     *
+     * Method by Manal Mohania
+     * */
+    private static boolean isPlacementAdjacent(String placement){
+
+        int coverage[][] = new int[26][26];
+        coverage[12][12] = 1;
+        coverage[12][13] = 1;
+
+        for (int i = 4; i < placement.length(); i += 4){
+            int col = placement.charAt(i) - 'A';
+            int row = placement.charAt(i + 1) - 'A';
+
+            if (coverage[col][row] != 0){
+                if (placement.charAt(i + 3) == 'A'){
+                    if (col == 25 || row == 25){
+                        return false;
+                    }
+                    if (!(coverage[1 + col][row] == coverage[col][1 + row] && coverage[1 + col][row] == coverage[col][row])) {
+                        return false;
+                    }
+                    coverage[col + 1][row]++;
+                    coverage[col][row + 1]++;
+                    coverage[col][row]++;
+                    continue;
+                }
+
+                else if (placement.charAt(i + 3) == 'B'){
+                    if (col == 0 || row == 25){return false;}
+                    if (!(coverage[col][row] == coverage[-1 + col][row] && coverage[-1 + col][row] == coverage[col][1 + row])) {
+                        return false;
+                    }
+                    coverage[-1 + col][row]++;
+                    coverage[col][row + 1]++;
+                    coverage[col][row]++;
+                    continue;
+                }
+
+                else if (placement.charAt(i + 3) == 'C'){
+                    if (col == 0 || row == 0){return false;}
+                    if (!(coverage[-1 + col][row] == coverage[col][row] && coverage[col][row] == coverage[col][-1 + row])) {
+                        return false;
+                    }
+                    coverage[-1 + col][row]++;
+                    coverage[col][-1 + row]++;
+                    coverage[col][row]++;
+                    continue;
+                }
+                else if (placement.charAt(i + 3) == 'D'){
+                    if (col == 25 || row == 0){return false;}
+                    if (!(coverage[1 + col][row] == coverage[col][row] && coverage[col][row] == coverage[col][-1 + row])) {
+                        return false;
+                    }
+                    coverage[col + 1][row]++;
+                    coverage[col][-1 + row]++;
+                    coverage[col][row]++;
+                    continue;
+                }
+            }
+
+            if (placement.charAt(i+3) == 'A'){
+                if ((col < 25 && coverage[1 + col][row] != 0) ||
+                        (row < 25 && coverage[col][1 + row] != 0)){
+                    return false;}
+                if ((!(2 + col < 26) || coverage[2 + col][row] == 0) &&
+                        (!(1 + col < 26 && row - 1 >= 0) || coverage[1 + col][-1 + row] == 0) &&
+                        (!(col + 1 < 26 && row + 1 < 26) || coverage[1 + col][1 + row] == 0) &&
+                        (!(row - 1 >= 0) || coverage[col][-1 + row] == 0) &&
+                        (!(row + 2 < 26) || coverage[col][2 + row] == 0) &&
+                        (!(col - 1 >= 0) || coverage[-1 + col][row] == 0) &&
+                        (!(col - 1 >= 0 && row + 1 < 26) || coverage[-1 + col][1 + row] == 0)){
+                    return false;
+                }
+
+                if (col == 25 || row == 25) {
+                    return false;
+                }
+                coverage[col][row] = 1;
+                coverage[1 + col][row] = 1;
+                coverage[col][1 + row] = 1;
+            }
+
+            else if (placement.charAt(i+3) == 'B'){
+                if ((col - 1 >= 0 && coverage[-1 + col][row] != 0) ||
+                        (row + 1 < 26 && coverage[col][1 + row] != 0)) {
+                    return false;
+                }
+                if (((!(1 + col < 26) || coverage[1 + col][row] == 0) &&
+                        (!(1 + col < 26 && 1 + row < 26) || coverage[1 + col][1 + row] == 0) &&
+                        (!(row - 1 >= 0) || coverage[col][-1 + row] == 0) &&
+                        (!(row + 2 < 26) || coverage[col][2 + row] == 0) &&
+                        (!(col - 1 >= 0 && row - 1 >= 0) || coverage[-1 + col][-1 + row] == 0) &&
+                        (!(col - 1 >= 0 && row + 1 < 26) || coverage[-1 + col][1 + row] == 0) &&
+                        (!(col - 2 >= 0) || coverage[-2 + col][row] == 0))) {
+                    return false;
+                }
+
+                if (col == 0 || row == 25) {
+                    return false;
+                }
+                coverage[col][row] = 1;
+                coverage[-1 + col][row] = 1;
+                coverage[col][1 + row] = 1;
+            }
+
+            else if (placement.charAt(i+3) == 'C'){
+                if ((col - 1 >= 0 && coverage[-1 + col][row] != 0) ||
+                        (row - 1 >= 0 && coverage[col][-1 + row] != 0)) {
+                    return false;
+                }
+                if ((!(col + 1 < 26) || coverage[1 + col][row] == 0 ) &&
+                        (!(col + 1 < 26 && row - 1 >= 0) || coverage[1 + col][-1 + row] == 0) &&
+                        (!(row - 2 >= 0) || coverage[col][-2 + row] == 0) &&
+                        (!(row + 1 < 26) || coverage[col][1 + row] == 0) &&
+                        (!(col - 1 >= 0 && row - 1 >= 0) || coverage[-1 + col][-1 + row] == 0) &&
+                        (!(col - 1 >= 0 && row + 1 < 26) || coverage[-1 + col][1 + row] == 0) &&
+                        (!(col - 2 >= 0) || coverage[-2 + col][row] == 0)){
+                    return false;
+                }
+
+                if (col == 0  || row == 0 ) {
+                    return false;
+                }
+                coverage[col][row] = 1;
+                coverage[-1 + col][row] = 1;
+                coverage[col][-1 + row] = 1;
+            }
+
+            else if (placement.charAt(i+3) == 'D'){
+                if ((col + 1 < 26 && coverage[1 + col][row] != 0) ||
+                        (row - 1 >= 0 && coverage[col][-1 + row] != 0)){
+                    return false;
+                }
+                if ((!(col + 2 < 26) || coverage[2 + col][row] == 0) &&
+                        (!(col + 1 < 26 && row - 1 >= 0) || coverage[1 + col][-1 + row] == 0) &&
+                        (!(col + 1 < 26 && row + 1 < 26) || coverage[1 + col][1 + row] == 0) &&
+                        (!(row - 2 >= 0) || coverage[col][-2 + row] == 0) &&
+                        (!(row + 1 < 26) || coverage[col][1 + row] == 0) &&
+                        (!(col - 1 >= 0) || coverage[-1 + col][row] == 0) &&
+                        (!(col - 1 >= 0 && row - 1 >= 0) || coverage[-1 + col][-1 + row] == 0)){
+                    return false;
+                }
+
+                if (col == 25 || row == 0) {
+                    return false;
+                }
+                coverage[col][row] = 1;
+                coverage[1 + col][row] = 1;
+                coverage[col][-1 + row] = 1;
+            }
+        }
+        return true;
+    }
+
+    /**
+     * Method by Joseph Meltzekr
+     * Combines the old tileStraddle and areColoursAlright methods into a single method.
+     * The placement string is therefore only looped through once for these methods, saving time.
+     * @param placement     The placement string to inspect.
+     * @return              Whether or not the placement is valid with respect to tile straddling and colour overlaps.
+     */
+    private static boolean straddleAndColours(String placement) {
+        int[][] tileTable = new int[26][26];
+        Colour[][] colourTable = new Colour[26][26];
+        colourTable[12][12] = RED;
+        colourTable[12][13] = GREEN;
+
+        for (int i=4; i < placement.length(); i+=4) {
+            int col = placement.charAt(i) - 'A';
+            int row = placement.charAt(i + 1) - 'A';
+
+            if ((colourTable[col][row] != RED || getColours(placement.charAt(i+2))[0] != GREEN) && (colourTable[col][row] != GREEN || getColours(placement.charAt(i+2))[0] != RED)) {
+                colourTable[col][row] = getColours(placement.charAt(i+2))[0];
+            }
+            else return false;
+
+            if (placement.charAt(i+3) == 'A'){
+                if (tileTable[col][row] == tileTable[col+1][row] && tileTable[col][row] == tileTable[col][row+1] && tileTable[col][row] != 0) return false;
+                tileTable[col][row] = i;
+                tileTable[col+1][row] = i;
+                tileTable[col][row+1] = i;
+
+                if ((colourTable[col+1][row] != RED || getColours(placement.charAt(i+2))[1] != GREEN) && (colourTable[col+1][row] != GREEN || getColours(placement.charAt(i+2))[1] != RED)) {
+                    colourTable[col+1][row] = getColours(placement.charAt(i+2))[1];
+                }
+                else return false;
+                if ((colourTable[col][row+1] != RED || getColours(placement.charAt(i+2))[2] != GREEN) && (colourTable[col][row+1] != GREEN || getColours(placement.charAt(i+2))[2] != RED)) {
+                    colourTable[col][row+1] = getColours(placement.charAt(i+2))[2];
+                }
+                else return false;
+            }
+            else if (placement.charAt(i + 3) == 'B'){
+                if (tileTable[col][row] == tileTable[col-1][row] && tileTable[col][row] == tileTable[col][row+1] && tileTable[col][row] != 0) return false;
+                tileTable[col][row] = i;
+                tileTable[col-1][row] = i;
+                tileTable[col][row+1] = i;
+
+                if ((colourTable[col][row+1] != RED || getColours(placement.charAt(i+2))[1] != GREEN) && (colourTable[col][row+1] != GREEN || getColours(placement.charAt(i+2))[1] != RED)) {
+                    colourTable[col][row+1] = getColours(placement.charAt(i+2))[1];
+                }
+                else return false;
+                if ((colourTable[col-1][row] != RED || getColours(placement.charAt(i+2))[2] != GREEN) && (colourTable[col-1][row] != GREEN || getColours(placement.charAt(i+2))[2] != RED)) {
+                    colourTable[col-1][row] = getColours(placement.charAt(i+2))[2];
+                }
+                else return false;
+            }
+            else if (placement.charAt(i + 3) == 'C'){
+                if (tileTable[col][row] == tileTable[col-1][row] && tileTable[col][row] == tileTable[col][row-1] && tileTable[col][row] != 0) return false;
+                tileTable[col][row] = i;
+                tileTable[col-1][row] = i;
+                tileTable[col][row-1] = i;
+
+                if ((colourTable[col-1][row] != RED || getColours(placement.charAt(i+2))[1] != GREEN) && (colourTable[col-1][row] != GREEN || getColours(placement.charAt(i+2))[1] != RED)) {
+                    colourTable[col-1][row] = getColours(placement.charAt(i+2))[1];
+                }
+                else return false;
+                if ((colourTable[col][row-1] != RED || getColours(placement.charAt(i+2))[2] != GREEN) && (colourTable[col][row-1] != GREEN || getColours(placement.charAt(i+2))[2] != RED)) {
+                    colourTable[col][row-1] = getColours(placement.charAt(i+2))[2];
+                }
+                else return false;
+            }
+            else if (placement.charAt(i + 3) == 'D'){
+                if (tileTable[col][row] == tileTable[col+1][row] && tileTable[col][row] == tileTable[col][row-1] && tileTable[col][row] != 0) return false;
+                tileTable[col][row] = i;
+                tileTable[col+1][row] = i;
+                tileTable[col][row-1] = i;
+
+                if ((colourTable[col][row-1] != RED || getColours(placement.charAt(i+2))[1] != GREEN) && (colourTable[col][row-1] != GREEN || getColours(placement.charAt(i+2))[1] != RED)) {
+                    colourTable[col][row-1] = getColours(placement.charAt(i+2))[1];
+                }
+                else return false;
+                if ((colourTable[col+1][row] != RED || getColours(placement.charAt(i+2))[2] != GREEN) && (colourTable[col+1][row] != GREEN || getColours(placement.charAt(i+2))[2] != RED)) {
+                    colourTable[col+1][row] = getColours(placement.charAt(i+2))[2];
+                }
+                else return false;
+            }
+        }
+        return true;
+    }
+
+    /**
+     * This method returns true if green has won the game given a valid placement string.
+     * Function written by Manal Mohania
+     * @param placement   The string of all the placements so far
+     */
+    static boolean greenHasWon(String placement){
+        return getWinner(placement);
     }

     /**
      * Determine the score for a player given a placement, following the
      * scoring rules for the game.
-     *
+     * Function written by Manal Mohania
      * @param placement A placement string
-     * @param green True if the score for the green player is requested,
-     *              otherwise the score for the red player should be returned
-     * @return the score for the requested player, given the placement
-     */
-    static int getScoreForPlacement(String placement, boolean green) {
-        // FIXME Task 7: determine the score for a player given a placement
-        return 0;
+     * @param green     True if the score for the green player is requested,
+     *                  otherwise the score for the red player should be returned
+     * @return          The score for the requested player, given the placement
+     */
+    public static int getScoreForPlacement(String placement, boolean green) {
+        /*I have this here for the moment but will remove it once main gets implemented*/
+        if (!isPlacementValid(placement))
+            return -1;
+
+        return getScore(placement, green);
     }

     /**
      * Generate a valid move that follows from: the given placement, a piece to
      * play, and the piece the opponent will play next.
+     * Search two deterministic levels and one probabilistic level into the game tree.
+     * Used when the AI difficulty setting is set to 'Hard'.
+     * Method by Joseph Meltzer
      *
      * @param placement  A valid placement string indicating a game state
      * @param piece  The piece you are to play ('A' to 'T')
      * @param opponentsPiece The piece your opponent will be asked to play next ('A' to 'T' or 0 if last move).
      * @return A string indicating a valid tile placement that represents your move.
      */
-    static String generateMove(String placement, char piece, char opponentsPiece) {
-        // FIXME Task 10: generate a valid move
-        return null;
+    public static String generateMove(String placement, char piece, char opponentsPiece) {
+        boolean green = (piece>='K' && piece<='T');
+        return alphabeta(placement, piece, opponentsPiece, 2, 1, -100, 1000, true, green).move;
+    }
+
+    /* Previous version of the generateMove function: used as a faster, but less powerful generator.
+    *  Its computation time is around 1 second, or less, per move.
+    *  Used exclusively when the AI difficulty setting is set to 'Medium'.*/
+    public static String genMoveMedium(String placement, char piece, char opponentsPiece) {
+        boolean green = (piece>='K' && piece<='T');
+        return comp1110.ass2.AI.alphabeta(placement, piece, opponentsPiece, 2, 0, -100, 1000, true, green).move;
+    }
+    /* Even older version of the generateMove function. Only looks at the immediately available moves.
+       Seemingly instant computation time.
+       Used exclusively when the AI difficulty setting is set to 'Easy'.*/
+
+    public static String genMoveEasy(String placement, char piece) {
+        String bestMove = "";
+        int bestScore = 0;
+        for (String move : validTiles(placement)) {
+            char x = move.charAt(0);
+            char y = move.charAt(1);
+            for (char o='A'; o<='D'; o++) {
+                if (piece>='A' && piece <='J') {
+                    if (isPlacementValid(placement + x + y + piece + o) && getScoreForPlacement(placement + x + y + piece + o, false)>bestScore ) {
+                        bestMove = ""+x+y+piece+o;
+                        bestScore = getScoreForPlacement(placement + x + y + piece + o, false);
+                    }
+                }
+                if (piece>='K' && piece <='T') {
+                    if (isPlacementValid(placement + x + y + piece + o) && getScoreForPlacement(placement + x + y + piece + o, true)>bestScore ) {
+                        bestMove = ""+x+y+piece+o;
+                        bestScore = getScoreForPlacement(placement + x + y + piece + o, true);
+                    }
+                }
+            }
+        }
+        return bestMove;
+    }
+
+    /**
+     * Function by Zhixian Wu, based off generateMove() by Joseph Meltzer
+     * @param placement   A valid placement string indicating the game state
+     * @param us          The player the AI is playing as
+     * @param opponent    The player the AI is opposing
+     * @return            placeholder
+     */
+    public static String genMoveCheating(String placement, Player us, Player opponent){
+        int depth = min(MAX_TILES-us.used_tiles-1, 3);
+        char piece = (char) (us.available_tiles).get(us.used_tiles);
+        boolean green = (piece>='K' && piece<='T');
+        if (depth>0) return alphabetaCheat(placement, us, opponent, depth, depth, -1000, 1000, true, green).move;
+        else {
+            return genMoveNotEasy(placement,piece);
+        }
+    }
+
+    /**
+     * Method by Joseph Meltzer
+     * @param placement  A valid placement string indicating the game state
+     * @param piece      The piece you are to play ('A' to 'T')
+     * @return           placeholder
+     */
+    private static String genMoveNotEasy(String placement, char piece) {
+        String bestMove = "";
+        int bestScore = -100;
+        for (char x='A'; x<='Z'; x++) {
+            for (char y='A'; y<='Z'; y++) {
+                for (char o='A'; o<='D'; o++) {
+                    if (piece>='A' && piece <='J') {
+                        if (isPlacementValid(placement + x + y + piece + o) && getScoreForPlacement(placement + x + y + piece + o, false)>bestScore ) {
+                            bestMove = ""+x+y+piece+o;
+                            bestScore = getScoreForPlacement(placement + x + y + piece + o, false)-getScoreForPlacement(placement + x + y + piece + o, true);
+                        }
+                    }
+                    if (piece>='K' && piece <='T') {
+                        if (isPlacementValid(placement + x + y + piece + o) && getScoreForPlacement(placement + x + y + piece + o, true)>bestScore ) {
+                            bestMove = ""+x+y+piece+o;
+                            bestScore = getScoreForPlacement(placement + x + y + piece + o, true)-getScoreForPlacement(placement + x + y + piece + o, false);
+                        }
+                    }
+                }
+            }
+        }
+        return bestMove;
     }
 }
diff -ru -x .git ../master/comp1140-ass2/src/comp1110/ass2/gui/Board.java comp1140-ass2/src/comp1110/ass2/gui/Board.java
--- ../master/comp1140-ass2/src/comp1110/ass2/gui/Board.java	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/src/comp1110/ass2/gui/Board.java	2016-10-15 14:00:01.000000000 +1100
@@ -1,22 +1,1526 @@
 package comp1110.ass2.gui;

+import comp1110.ass2.*;
 import javafx.application.Application;
+import javafx.geometry.HPos;
+import javafx.geometry.Insets;
+import javafx.geometry.VPos;
+import javafx.scene.Group;
+import javafx.scene.Scene;
+import javafx.scene.control.Button;
+import javafx.scene.control.ScrollPane;
+import javafx.scene.effect.DropShadow;
+import javafx.scene.image.Image;
+import javafx.scene.image.ImageView;
+import javafx.scene.input.MouseEvent;
+import javafx.scene.layout.*;
+import javafx.scene.media.AudioClip;
+import javafx.scene.paint.Color;
+import javafx.scene.shape.Rectangle;
+import javafx.scene.text.Font;
+import javafx.scene.text.FontWeight;
+import javafx.scene.text.Text;
 import javafx.stage.Stage;
+import comp1110.ass2.StratoGame;
+
+
+import static comp1110.ass2.Colour.BLACK;
+import static comp1110.ass2.Colour.GREEN;
+import static comp1110.ass2.Difficulty.*;
+import static comp1110.ass2.Player.MAX_TILES;
+import static comp1110.ass2.StratoGame.*;
+import static javafx.scene.paint.Color.*;

 public class Board extends Application {
+
+/*The majority of this class was done by Zhixian Wu, with some functions and
+layout by Manal Mohania and Joseph Meltzer*/
+
+/*All elements using CSS was done by Manal Mohania, with inspiration and any
+* pieces of adapted code in the G-originality*/
+
+/*OVERVIEW: The first function called by the stage is initialSettings(), which
+* creates the first screen many buttons to choose the playing mode (Human v.s
+* Hard AI, Human vs. Human, Easy AI vs. Cheating AI, etc.
+* (And buttons for the instructions, sound, etc.)*/
+
+/*The difficulty buttons change the GameState, which changes what kind of board
+* is created, what difficulty AI to call, etc. */
+
+/*Whenyou click 'Start', makeGame() is called
+* makeGame() calls makeControls() and makeBoard(). */
+
+/*makeControls() is pretty much the same for all playing modes, except it omits
+* a "Rotate" button if it's single-player. And if the game is AI vs. AI, it creates
+* a button Next Move which progresses the game when clicked*/
+
+/*makeBoard() is pretty much the same again.
+* makeBoard() modifies some GridPanes:
+ * 1. playingBoard so it looks like a board
+ * 2. heightLabels so its rows and columns align with the playingBoard
+ * 3. clickablePanes so its rows and columns align with the playingBoard
+ * The big difference is that depending if the game is Human vs. AI, AI vs. Human,
+ * or Human vs. Human, it calls different addPane function: addPanePlayerGreen,
+ * addPanePlayerRed, or addPaneTwoPlayer.
+ * If it's AI vs. Human, it also makes the AI's first move by calling makeAIMove.
+ * If it's AI vs. AI, it doesn't add clickable panes to the board*/
+
+/*Each 'addPane' function creates a pane at the specified row and column
+* on the GridPane clickablePanes.
+* When a pane is clicked, the two player version of the function makes a
+* move (by calling makeGUIPlacement) based on whose turn it is.
+* The one player version makes the player's move and calls makeAIMove().
+* These panes also hold the events for the previews of tile placements*/
+
+/*Apart from putting an image in the right place, the makeGUIPlacement function
+* modifies whose turn it is, how far through their stack of tiles each player
+* is at, the heights of the tiles (by calling displayHeights()), what the current
+* score is (by calling updateScores()), check if the game is over and display
+* the Game Over Screen if it is, etc.*/
+
+
+
     private static final int BOARD_WIDTH = 933;
     private static final int BOARD_HEIGHT = 700;
+    private static final String URI_BASE = "assets/";
+    private static final int TILE_SIZE = 25; /*This is half the width of each tile*/
+    private static final int BOARD_SIZE = 26; /*As in a 26x26 board*/
+    private static final String PLACEMENT_URI = Viewer.class.getResource(URI_BASE
+            + "sound.wav").toString();
+
+    /*Objects that need to be accessible to many functions.*/
+    private GameState gameState;
+    private Player playerG;
+    private Player playerR;
+
+    /*Nodes that need to be accessible by many functions.*/
+    private ImageView ivg = new ImageView();
+    private ImageView ivr = new ImageView();
+    private Text greentxt = new Text("Green");
+    private Text redtxt = new Text("Red");
+    private Button rotateG = new Button("Rotate");
+    private Button rotateR = new Button("Rotate");
+    private Button nextMove = new Button("Next Move");
+    private Text errorMessage = new Text("Invalid move!");
+    private Text aiThink = new Text("Thinking...");
+    private Text redScore = new Text("1");
+    private Text greenScore = new Text("1");
+    private Text redTilesLeft = new Text("");
+    private Text greenTilesLeft = new Text("");
+    private ImageView sound_icon = new ImageView();
+
+    /*Various Groups that organise the screen.*/
+    private final Group root = new Group();
+    private final Group popUp1 = new Group();
+    private final Group popUp2 = new Group();
+    private final Group controls = new Group();
+    private final GridPane playerControls = new GridPane();
+    private final Group placementGrp = new Group();
+    private final GridPane playingBoard = new GridPane();
+    private final GridPane heightLabels = new GridPane();
+    private final GridPane clickablePanes = new GridPane();
+
+    /*We change the background colour the the scene in the game*/
+    private Scene scene;
+
+    /*A counter that tells you if this is the first game played*/
+    private boolean firstGame = true;
+    /*If the sound is muted*/
+    private boolean soundOn = true;
+
+    /*the audio clip played when a placement is made*/
+    /*The sound was downloaded from
+    * <http://www.sounds.beachware.com/2illionzayp3may/jspjrz/SWITCH.mp3>*/
+    private final AudioClip audio = new AudioClip(PLACEMENT_URI);
+
+
+    /*Function mostly by Zhixian Wu, with all button styling by Manal Mohania*/
+    private void initialSettings() {
+
+        /*The scene actually changes colour from the start screen to the actual game.
+        * This was done by Manal Mohania*/
+        scene.setFill(Color.LIGHTGREY);
+        /*Set the opacity back to normal and re-enable a button after the last game ended*/
+        placementGrp.setOpacity(1);
+        playingBoard.setOpacity(1);
+        heightLabels.setOpacity(1);
+        nextMove.setDisable(false);
+
+        /*A new game*/
+        gameState = new GameState(BLACK, HUMAN, HUMAN);
+
+        /*The Stratopolis logo on the start screen*/
+        ImageView logo = new ImageView();
+        logo.setImage(new Image(Viewer.class.getResource(URI_BASE + "stratopolis"
+                + ".png").toString()));
+        placementGrp.getChildren().add(logo);
+        logo.setLayoutX(220);
+        logo.setLayoutY(180);
+
+     /*The options for the game: Human vs Hard AI, etc.*/
+        /*This is the text describing these options*/
+        Text greenText = new Text("Player Green: Human");
+        greenText.setFill(Color.GREEN);
+        greenText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
+
+        Text green1 = new Text("Human: ");
+        green1.setFill(Color.GREEN);
+        green1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
+
+        Text green2 = new Text("AI:   ");
+        green2.setFill(Color.GREEN);
+        green2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
+
+        Text redText = new Text("Player Red: Human");
+        redText.setFill(Color.RED);
+        redText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
+
+        Text red1 = new Text("Human: ");
+        red1.setFill(Color.RED);
+        red1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
+
+        Text red2 = new Text("AI:   ");
+        red2.setFill(Color.RED);
+        red2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
+
+        /*Each of these buttons tell the game which players you want to be
+        * human, and which to be AIs*/
+        Button greenHuman = new Button("Human");
+        greenHuman.setOnAction(event-> {
+            gameState.greenPlayer = HUMAN;
+            greenText.setText("Player Green: Human");
+        });
+
+        Button greenEasy = new Button("Easy");
+        greenEasy.setOnAction(event-> {
+            gameState.greenPlayer = EASY;
+            greenText.setText("Player Green: Easy");
+        });
+
+        Button greenMedium = new Button("Medium");
+        greenMedium.setOnAction(event-> {
+            gameState.greenPlayer = MEDIUM;
+            greenText.setText("Player Green: Medium");
+        });
+
+        Button greenHard = new Button("Hard");
+        greenHard.setOnAction(event-> {
+            gameState.greenPlayer = HARD;
+            greenText.setText("Player Green: Hard");
+        });
+
+        Button greenCheating = new Button("Cheating");
+        greenCheating.setOnAction(event-> {
+            gameState.greenPlayer = CHEATING;
+            greenText.setText("Player Green: Cheating");
+        });
+
+        Button redHuman = new Button("Human");
+        redHuman.setOnAction(event-> {
+            gameState.redPlayer = HUMAN;
+            redText.setText("Player Red: Human");
+        });
+
+        Button redEasy = new Button("Easy");
+        redEasy.setOnAction(event-> {
+            gameState.redPlayer = EASY;
+            redText.setText("Player Red: Easy");
+        });
+
+        Button redMedium = new Button("Medium");
+        redMedium.setOnAction(event-> {
+            gameState.redPlayer = MEDIUM;
+            redText.setText("Player Red: Medium");
+        });
+
+        Button redHard = new Button("Hard");
+        redHard.setOnAction(event-> {
+            gameState.redPlayer = HARD;
+            redText.setText("Player Red: Hard");
+        });
+
+        Button redCheating = new Button("Cheating");
+        redCheating.setOnAction(event-> {
+            gameState.redPlayer = CHEATING;
+            redText.setText("Player Red: Cheating");
+        });
+
+        DropShadow shadow = new DropShadow();
+        for (Button b : new Button[] {greenHuman, greenEasy, greenMedium, greenHard, greenCheating,
+                                      redHuman, redEasy, redMedium, redHard, redCheating}) {
+            b.setStyle("-fx-font: 14 Arial; -fx-background-color: \n" +
+                    "        #090a0c,\n" +
+                    "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
+                    "        linear-gradient(#20262b, #191d22),\n" +
+                    "        radial-gradient(center 50% 0%, radius 100%, " +
+                    "rgba(114,131,148,0.9), rgba(255,255,255,0));" +
+                    "-fx-text-fill: white;");
+            b.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> b.setEffect(shadow));
+            b.addEventHandler(MouseEvent.MOUSE_EXITED, event -> b.setEffect(null));
+        }
+
+        /*Layout of the options by Zhixian Wu and Manal Mohania*/
+        HBox ghb1 = new HBox(5);
+        ghb1.getChildren().addAll(green1,greenHuman);
+        HBox ghb2 = new HBox(5);
+        ghb2.getChildren().addAll(green2,greenEasy,greenMedium,greenHard,greenCheating);
+        ghb2.setMargin(green2, new Insets(0,28,0,0));
+        ghb2.setSpacing(7);
+        VBox green = new VBox(5);
+        green.getChildren().addAll(greenText,ghb1,ghb2);
+        green.setSpacing(15);
+        green.setLayoutX(30);
+        green.setLayoutY(480);
+
+        HBox rhb1 = new HBox(5);
+        rhb1.getChildren().addAll(red1,redHuman);
+        HBox rhb2 = new HBox(5);
+        rhb2.getChildren().addAll(red2,redEasy,redMedium,redHard,redCheating);
+        rhb2.setMargin(red2, new Insets(0,28,0,0));
+        rhb2.setSpacing(7);
+        VBox red = new VBox(5);
+        red.getChildren().addAll(redText,rhb1,rhb2);
+        red.setSpacing(15);
+        red.setLayoutX(540);
+        red.setLayoutY(480);
+
+
+        /*This button starts the game*/
+        Button startGame = new Button("Start");
+        startGame.setOnAction(event-> {
+            placementGrp.getChildren().clear();
+            makeGame();
+        });
+        startGame.setStyle("-fx-background-color: \n" +
+                "        linear-gradient(#ffd65b, #e68400),\n" +
+                "        linear-gradient(#ffef84, #f2ba44),\n" +
+                "        linear-gradient(#ffea6a, #efaa22),\n" +
+                "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
+                "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9)," +
+                " rgba(255,255,255,0));\n" +
+                "    -fx-background-radius: 30;\n" +
+                "    -fx-background-insets: 0,1,2,3,0;\n" +
+                "    -fx-text-fill: #654b00;\n" +
+                "    -fx-font-weight: bold;\n" +
+                "    -fx-font-size: 26px;\n" +
+                "    -fx-padding: 10 25 10 25;");
+
+        startGame.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> startGame.setEffect(shadow));
+        startGame.addEventHandler(MouseEvent.MOUSE_EXITED, event -> startGame.setEffect(null));
+        startGame.setLayoutX(400);
+        startGame.setLayoutY(620);
+
+        /*The mute button by Zhixian Wu*/
+        /*The mute button's image*/
+        /*The image for unmuted sound is from
+        * <https://pixabay.com/en/icon-loudspeaker-speaker-horn-1628258/>
+        * The image from the muted sound is an edited version of that image*/
+        if (soundOn) sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon"
+                + ".png").toString()));
+        else sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon_off"
+                + ".png").toString()));
+        sound_icon.setFitWidth(25);
+        sound_icon.setPreserveRatio(true);
+        sound_icon.setSmooth(true);
+        sound_icon.setCache(true);
+        sound_icon.setLayoutX(900);
+        sound_icon.setLayoutY(15);
+        /*The mute button's clickable pane*/
+        Pane sound_pane = new Pane();
+        sound_pane.setPrefSize(25,25);
+        sound_pane.setOnMouseClicked(event -> {
+            if (soundOn){
+                sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon_off"
+                        + ".png").toString()));
+                sound_icon.setFitWidth(25);
+                sound_icon.setPreserveRatio(true);
+                sound_icon.setSmooth(true);
+                sound_icon.setCache(true);
+                soundOn = false;
+            } else{
+                sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon"
+                        + ".png").toString()));
+                sound_icon.setFitWidth(25);
+                sound_icon.setPreserveRatio(true);
+                sound_icon.setSmooth(true);
+                sound_icon.setCache(true);
+                soundOn = true;
+            }
+        });
+        sound_pane.setLayoutX(900);
+        sound_pane.setLayoutY(15);
+
+        /*The button that brings up the instructions*/
+        Button instructions = new Button("?");
+        instructions.setOnAction(event-> getInstructions());
+        instructions.setStyle("-fx-background-color: #9932cc;" +
+                "-fx-background-radius: 55em; " +
+                "-fx-min-width: 30px; " +
+                "-fx-min-height: 30px; " +
+                "-fx-max-width: 30px; " +
+                "-fx-max-height: 30px;" +
+                "-fx-text-fill: #ffd65b"
+        );
+        instructions.addEventHandler(MouseEvent.MOUSE_ENTERED, event ->
+                instructions.setEffect(shadow));
+        instructions.addEventHandler(MouseEvent.MOUSE_EXITED, event ->
+                instructions.setEffect(null));
+        instructions.setLayoutX(860);
+        instructions.setLayoutY(12);
+
+        placementGrp.getChildren().addAll(green, red, startGame, instructions,
+                sound_icon, sound_pane);
+    }
+
+    /*Function by Zhixian Wu. This function displays the instructions when called.*/
+    private void getInstructions(){
+        /*Disable the interactive stuff from these groups when the instructions are up*/
+        placementGrp.setDisable(true);
+        controls.setDisable(true);
+
+        /*Layout*/
+        GridPane mainInstruc = new GridPane();
+        mainInstruc.setLayoutY(90);
+        mainInstruc.setLayoutX(130);
+        mainInstruc.setHgap(5);
+        mainInstruc.setVgap(5);
+        /*A nice outline around the instructions*/
+        Rectangle thickBorder = new Rectangle(655,470,Color.BEIGE);
+        thickBorder.setArcHeight(7);
+        thickBorder.setArcWidth(7);
+        thickBorder.setLayoutX(120);
+        thickBorder.setLayoutY(80);
+        thickBorder.setOpacity(0.5);
+
+        /*Some of the instructions copied from
+        * <https://boardgamegeek.com/boardgame/125022/stratopolis>*/
+        Text instructions = new Text("\n  Stratopolis a two player strategy based game." +
+                " Each player starts the game with twenty L-shaped tiles, each comprising "
+                + "three squares; one player has tiles showing all green squares, green and "
+                + "neutral squares, or two green squares and one red square, while the other "
+                + "player's tiles reverse red and green. Players shuffle and stack these "
+                + "tiles face down, revealing only the topmost tile. \n \n"
+                + "  To start the game, a two-square tile (one red, one green) is placed "
+                + "on the table. Players then take turns adding their topmost tile to "
+                + "the display. A placement is made by clicking on the board. Invalid placements "
+                + "have duller preview images. \n\n"
+                + "  Apart from using the 'Rotate' button, the tiles can be rotated by scrolling "
+                + "while hovering over the board.\n\n"
+                + "  A tile can be placed (1) on the table with at least one "
+                + "edge adjacent to an edge in play or (2) on top of at least two tiles "
+                + "already in play. When placed on a higher level, each square of the "
+                + "tile must be supported, the tile must be level, and red and green "
+                + "tile must be supported, the tile must be level, and red and green "
+                + "squares cannot cover one another. (Every other color play – such as "
+                + "green on neutral or red on red – is legal.)\n \n"
+                + "  Each player's score is the area of the largest connected area of "
+                + "their colour, times the maximum height of that area.\n \n"
+                + "  If a tie breaker is required, the next largest area for both "
+                + "players is used. If a tie breaker is still required, we go on "
+                + "to the next largest area, and so on. If this does not produce a "
+                + "winner, the winner is determined randomly.\n\n"
+                + "  You can choose a two-player game, play against an AI of any "
+                + "difficulty (the cheating AI can peek into both your decks), or "
+                + "watch two AIs play against each other. The two-AI game is advanced "
+                + "by clicking the 'Next Move' button. \n"
+        );
+
+        instructions.setFont(Font.font("Arial", 16));
+        instructions.setWrappingWidth(610);
+
+        /*The scroll-capable pane the instructions go in*/
+        ScrollPane scroll = new ScrollPane();
+        scroll.setContent(instructions);
+        scroll.setPrefViewportHeight(400.0);
+        scroll.setPrefViewportWidth(620.0);
+
+        /*The button that removes the instructions and makes the groups interactive again*/
+        Button exitBtn = new Button("x");
+        exitBtn.setOnAction(event->  {
+            root.getChildren().remove(popUp1);
+            placementGrp.setDisable(false);
+            controls.setDisable(false);
+        } );
+        exitBtn.setStyle("-fx-font: 14 Arial; -fx-background-color: \n" +
+                "        #090a0c,\n" +
+                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
+                "        linear-gradient(#20262b, #191d22),\n" +
+                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), " +
+                "  rgba(255,255,255,0));" +
+                "-fx-text-fill: white;");
+        DropShadow shadow = new DropShadow();
+        exitBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, event ->  exitBtn.setEffect(shadow));
+        exitBtn.addEventHandler(MouseEvent.MOUSE_EXITED, event -> exitBtn.setEffect(null));
+
+        /*Layout*/
+        mainInstruc.getChildren().addAll(scroll,exitBtn);
+        GridPane.setRowIndex(scroll,1);
+        GridPane.setColumnIndex(scroll,0);
+        GridPane.setRowIndex(exitBtn,0);
+        GridPane.setColumnIndex(exitBtn,0);
+        GridPane.setHalignment(exitBtn, HPos.RIGHT);
+
+        popUp1.getChildren().addAll(thickBorder,mainInstruc);
+        root.getChildren().add(popUp1);
+    }
+
+    /*Function by Zhixian Wu*/
+    private void makeGame(){
+        playerG = new PlayerG();
+        playerR = new PlayerR();
+
+        /*Make the playing board*/
+        makeBoard();
+
+        /*Makes the controls for the game, separately from the board*/
+        makeControls();
+    }
+
+    /*Function mostly by Zhixian Wu, with the running score and button styling by Manal Mohania*/
+    private void makeControls(){
+
+        scene.setFill(Color.WHITESMOKE);
+        /*Make the control pane as a GridPane. This is the stuff on the right*/
+        playerControls.setPrefSize(120, 200);
+        playerControls.setMaxSize(120, 200);
+
+        /*The text labeling Green and Red's tiles, which you see on the right*/
+        greentxt.setFill(Color.GREEN);
+        greentxt.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
+
+        redtxt.setFill(Color.RED);
+        redtxt.setFont(Font.font("Verdana", 16));
+
+        /*The mute button's image*/
+        if (soundOn) sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon"
+                + ".png").toString()));
+        else sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon_off"
+                + ".png").toString()));
+        sound_icon.setFitWidth(25);
+        sound_icon.setPreserveRatio(true);
+        sound_icon.setSmooth(true);
+        sound_icon.setCache(true);
+        sound_icon.setLayoutX(900);
+        sound_icon.setLayoutY(15);
+        /*The mute button's clickable pane*/
+        Pane sound_pane = new Pane();
+        sound_pane.setPrefSize(25,25);
+        sound_pane.setOnMouseClicked(event -> {
+            if (soundOn){
+                sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon_off"
+                        + ".png").toString()));
+                sound_icon.setFitWidth(25);
+                sound_icon.setPreserveRatio(true);
+                sound_icon.setSmooth(true);
+                sound_icon.setCache(true);
+                soundOn = false;
+            } else{
+                sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon"
+                        + ".png").toString()));
+                sound_icon.setFitWidth(25);
+                sound_icon.setPreserveRatio(true);
+                sound_icon.setSmooth(true);
+                sound_icon.setCache(true);
+                soundOn = true;
+            }
+        });
+        sound_pane.setLayoutX(900);
+        sound_pane.setLayoutY(15);
+
+        /*The button that brings up the instructions*/
+        Button instructions = new Button("?");
+        instructions.setOnAction(event-> getInstructions());
+        instructions.setStyle("-fx-background-color: #9932cc;" +
+                "-fx-background-radius: 55em; " +
+                "-fx-min-width: 30px; " +
+                "-fx-min-height: 30px; " +
+                "-fx-max-width: 30px; " +
+                "-fx-max-height: 30px;" +
+                "-fx-text-fill: #ffd65b"
+        );
+        DropShadow shadow = new DropShadow();
+        instructions.addEventHandler(MouseEvent.MOUSE_ENTERED, event ->
+                instructions.setEffect(shadow));
+        instructions.addEventHandler(MouseEvent.MOUSE_EXITED, event ->
+                instructions.setEffect(null));
+        instructions.setLayoutX(860);
+        instructions.setLayoutY(12);
+
+        controls.getChildren().addAll(sound_icon,sound_pane,instructions);
+
+        /*The tiles at the "top" of each player's "stack", displayed on the right*/
+        ivg.setImage(new Image(Viewer.class.getResource(URI_BASE +
+                (playerG.available_tiles).get(playerG.used_tiles) + ".png").toString()));
+        ivg.setRotate((((int) (playerG.rotation)-'A')*90));
+        ivg.setFitWidth(80);
+        ivg.setPreserveRatio(true);
+        ivg.setSmooth(true);
+        ivg.setCache(true);
+
+        ivr.setImage(new Image(Viewer.class.getResource(URI_BASE +
+                (playerR.available_tiles).get(playerR.used_tiles) + ".png").toString()));
+        ivr.setRotate((((int) (playerR.rotation)-'A')*90));
+        ivr.setFitWidth(80);
+        ivr.setPreserveRatio(true);
+        ivr.setSmooth(true);
+        ivr.setCache(true);
+
+        /*The events for the buttons that rotate the tiles*/
+        rotateG.setOnAction(event-> {
+            playerG.rotateTile();
+            ivg.setRotate((((int) (playerG.rotation)-'A')*90));
+        });
+        rotateR.setOnAction(event-> {
+            playerR.rotateTile();
+            ivr.setRotate((((int) (playerR.rotation)-'A')*90));
+        });
+
+
+
+        /*Adding the nodes. We may omit the a rotate button depending on the playingMode*/
+        playerControls.getChildren().addAll(greentxt,redtxt,ivg,ivr);
+        if (gameState.greenPlayer==HUMAN) playerControls.getChildren().add(rotateG);
+        if (gameState.redPlayer==HUMAN) playerControls.getChildren().add(rotateR);
+
+        /*Layout for the controls*/
+        GridPane.setColumnIndex(ivg,0);
+        GridPane.setRowIndex(ivg,0);
+        GridPane.setColumnIndex(ivr,1);
+        GridPane.setRowIndex(ivr,0);
+        GridPane.setColumnIndex(rotateG,0);
+        GridPane.setRowIndex(rotateG,1);
+        GridPane.setColumnIndex(rotateR,1);
+        GridPane.setRowIndex(rotateR,1);
+        GridPane.setColumnIndex(greentxt,0);
+        GridPane.setRowIndex(greentxt,2);
+        GridPane.setColumnIndex(redtxt,1);
+        GridPane.setRowIndex(redtxt,2);
+
+        playerControls.setLayoutX(TILE_SIZE*BOARD_SIZE+85);
+        playerControls.setLayoutY(200);
+
+        playerControls.setHgap(10);
+        playerControls.setVgap(10);
+
+        controls.getChildren().add(playerControls);
+
+        /*This line is for debugging purposes only. When set to true, it shows grid lines*/
+        playerControls.setGridLinesVisible(false);
+
+
+        /*A main menu button. It clears the current game and calls initialSettings()*/
+        Button menu = new Button("Main Menu");
+        menu.setOnAction(event->{
+            placementGrp.setOpacity(1);
+            controls.getChildren().clear();
+            placementGrp.getChildren().clear();
+            playingBoard.getChildren().clear();
+            heightLabels.getChildren().clear();
+            clickablePanes.getChildren().clear();
+            playerControls.getChildren().clear();
+            root.getChildren().remove(popUp2);
+
+            firstGame = false;
+
+            initialSettings();
+        });
+        controls.getChildren().add(menu);
+        menu.setLayoutX(820);
+        menu.setLayoutY(650);
+
+        menu.setStyle("-fx-font: 14 Arial; -fx-background-color: \n" +
+                "        #090a0c,\n" +
+                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
+                "        linear-gradient(#20262b, #191d22),\n" +
+                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), " +
+                "    rgba(255,255,255,0));" +
+                "-fx-text-fill: white;");
+
+        menu.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> menu.setEffect(shadow));
+        menu.addEventHandler(MouseEvent.MOUSE_EXITED, event -> menu.setEffect(null));
+
+        /*Scores by Manal Mohania*/
+        Rectangle r = new Rectangle(170,80,Color.SANDYBROWN);
+        r.setLayoutY(50);
+        r.setLayoutX(735);
+        r.setArcHeight(20);
+        r.setArcWidth(20);
+        controls.getChildren().add(r);
+
+        Text score = new Text("SCORES");
+        score.setLayoutX(800);
+        score.setLayoutY(65);
+        controls.getChildren().add(score);
+
+        greenScore.setLayoutX(750);
+        greenScore.setLayoutY(110);
+        greenScore.setFill(Color.GREEN);
+        greenScore.setFont(Font.font("Gentium", 44));
+
+        redScore.setLayoutX(830);
+        redScore.setLayoutY(110);
+        redScore.setFill(Color.RED);
+        redScore.setFont(Font.font("Gentium", 44));
+        controls.getChildren().addAll(greenScore,redScore);
+        updateScores();
+
+        /*Counter of tiles left by Zhixian Wu*/
+        Text tiles_left = new Text("TILES LEFT");
+
+        greenTilesLeft.setFill(Color.GREEN);
+        greenTilesLeft.setFont(Font.font("Gentium", 24));
+
+        redTilesLeft.setFill(Color.RED);
+        redTilesLeft.setFont(Font.font("Gentium", 24));
+
+        updateTilesLeft();
+
+        GridPane tileCounter = new GridPane();
+        tileCounter.getChildren().addAll(tiles_left,greenTilesLeft,redTilesLeft);
+        for (int i = 0; i < 2; i++) {
+            ColumnConstraints column = new ColumnConstraints(85);
+            tileCounter.getColumnConstraints().add(column);
+        }
+        /*Layout of tiles left*/
+        GridPane.setColumnIndex(tiles_left,0);
+        GridPane.setRowIndex(tiles_left,0);
+        GridPane.setColumnSpan(tiles_left,2);
+        GridPane.setHalignment(tiles_left, HPos.CENTER);
+        GridPane.setValignment(tiles_left, VPos.BOTTOM);
+        GridPane.setColumnIndex(greenTilesLeft,0);
+        GridPane.setRowIndex(greenTilesLeft,1);
+        GridPane.setHalignment(greenTilesLeft, HPos.CENTER);
+        GridPane.setValignment(greenTilesLeft, VPos.TOP);
+        GridPane.setColumnIndex(redTilesLeft,1);
+        GridPane.setRowIndex(redTilesLeft,1);
+        GridPane.setHalignment(redTilesLeft, HPos.CENTER);
+        GridPane.setValignment(redTilesLeft, VPos.TOP);
+
+        tileCounter.setLayoutX(TILE_SIZE*BOARD_SIZE+70);
+        tileCounter.setLayoutY(360);
+        controls.getChildren().add(tileCounter);

-    // FIXME Task 8: Implement a basic playable Strato Game in JavaFX that only allows pieces to be placed in valid places
+        /*If both players are AI: */
+        if (gameState.greenPlayer!=HUMAN && gameState.redPlayer!=HUMAN) {
+            /*Makes the first move*/
+            makeGUIPlacement("MMUA");

-    // FIXME Task 9: Implement scoring
+            /*The button that tells the AI to make a move, click this to progress the game*/
+            nextMove.setOnMousePressed(event->  {
+                if (gameState.moveHistory.length()<=MAX_TILES*8){
+                    aiThink.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
+                    controls.getChildren().add(aiThink);
+                    aiThink.setLayoutX(750);
+                    aiThink.setLayoutY(450);
+                }
+            });
+            nextMove.setOnAction(event->  makeAIMove());

-    // FIXME Task 11: Implement a game that can play valid moves (even if they are weak moves)
+            nextMove.setStyle("-fx-font: 14 Arial; -fx-background-color: \n" +
+                    "        #090a0c,\n" +
+                    "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
+                    "        linear-gradient(#20262b, #191d22),\n" +
+                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), " +
+                    "    rgba(255,255,255,0));" +
+                    "-fx-text-fill: white;");

-    // FIXME Task 12: Implement a game that can play good moves
+            nextMove.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> nextMove.setEffect(shadow));
+            nextMove.addEventHandler(MouseEvent.MOUSE_EXITED, event -> nextMove.setEffect(null));
+            nextMove.setLayoutX(TILE_SIZE*BOARD_SIZE+60);
+            nextMove.setLayoutY(650);
+            controls.getChildren().add(nextMove);
+        }
+
+    }
+    /*Function by Zhixian Wu*/
+    /*It updates the TILES LEFT field on the board*/
+    private void updateTilesLeft(){
+        if (gameState.moveHistory.length()<=MAX_TILES*8-4){
+            String green = Integer.toString(MAX_TILES-playerG.used_tiles);
+            String red = Integer.toString(MAX_TILES-playerR.used_tiles);
+            greenTilesLeft.setText(green);
+            redTilesLeft.setText(red);
+        } else{
+            if (MAX_TILES*8-4<=gameState.moveHistory.length() &&
+                    gameState.moveHistory.length()<=MAX_TILES*8){
+                greenTilesLeft.setText("0");
+                String red = Integer.toString(MAX_TILES-playerR.used_tiles);
+                redTilesLeft.setText(red);
+            } else{
+                greenTilesLeft.setText("0");
+                redTilesLeft.setText("0");
+            }
+        }
+
+    }
+
+    /*Function mostly by Zhixian Wu, with minor changes by Manal Mohania (indicated below)*/
+    private void makeBoard(){
+        int size = TILE_SIZE * BOARD_SIZE;
+        int offset = (BOARD_HEIGHT - size) / 2;
+        playingBoard.setPrefSize(size, size);
+        playingBoard.setMaxSize(size, size);
+
+        if (firstGame){
+            /*determines the size of the rows and columns of the playing board*/
+            for (int i = 0; i < BOARD_SIZE; i++) {
+                RowConstraints row = new RowConstraints(TILE_SIZE);
+                playingBoard.getRowConstraints().add(row);
+            }
+            for (int i = 0; i < BOARD_SIZE; i++) {
+                ColumnConstraints column = new ColumnConstraints(TILE_SIZE);
+                playingBoard.getColumnConstraints().add(column);
+            }
+        }
+
+        /*Makes the board background black using CSS*/
+        playingBoard.setStyle("-fx-background-color: black");
+
+        /*Creates white squares on a black background for the board*/
+        for (int i=0; i<BOARD_SIZE;i++){
+            for (int j=0; j<BOARD_SIZE; j++){
+                int rectSize = TILE_SIZE-2;
+                Rectangle r = new Rectangle(rectSize, rectSize);
+                r.setFill(Color.web("rgb(230,228,221)")); /*Colour done by Manal Mohania*/
+                playingBoard.getChildren().add(r);
+                GridPane.setRowIndex(r,i);
+                GridPane.setColumnIndex(r,j);
+                GridPane.setHalignment(r, HPos.CENTER);
+                GridPane.setValignment(r, VPos.CENTER);
+            }
+        }
+        /*Give the board thicker outer edges*/
+        Rectangle thickBorder = new Rectangle(size+8,size+8,Color.BLACK);
+        thickBorder.setArcHeight(7);
+        thickBorder.setArcWidth(7);
+        thickBorder.setLayoutX(offset-4);
+        thickBorder.setLayoutY(offset-4);
+
+        /*This line is for debugging purposes only. When set to true, it shows grid lines*/
+        playingBoard.setGridLinesVisible(false);
+
+        /*Layout*/
+        playingBoard.setLayoutX(offset);
+        playingBoard.setLayoutY(offset);
+
+        /*An GridPane on top of playingBoard, laid out identically to playingBoard
+         that shows the height of the tile on that position*/
+        heightLabels.setPrefSize(size, size);
+        heightLabels.setMaxSize(size, size);
+        if (firstGame){
+            /*Determines the size of the grid rows and columns*/
+            for (int i = 0; i < BOARD_SIZE; i++) {
+                RowConstraints row = new RowConstraints(TILE_SIZE);
+                heightLabels.getRowConstraints().add(row);
+            }
+            for (int i = 0; i < BOARD_SIZE; i++) {
+                ColumnConstraints column = new ColumnConstraints(TILE_SIZE);
+                heightLabels.getColumnConstraints().add(column);
+            }
+        }
+        /*Layout*/
+        heightLabels.setLayoutX(offset);
+        heightLabels.setLayoutY(offset);
+
+        /*A GridPane on top of playingBoard and heightLabels, laid out identically to playingBoard,
+         holding the interactive tiles for the game*/
+        clickablePanes.setPrefSize(size, size);
+        clickablePanes.setMaxSize(size, size);
+        if (firstGame){
+            /*Determines the size of the grid rows and columns*/
+            for (int i = 0; i < BOARD_SIZE; i++) {
+                RowConstraints row = new RowConstraints(TILE_SIZE);
+                clickablePanes.getRowConstraints().add(row);
+            }
+            for (int i = 0; i < BOARD_SIZE; i++) {
+                ColumnConstraints column = new ColumnConstraints(TILE_SIZE);
+                clickablePanes.getColumnConstraints().add(column);
+            }
+        }
+
+        /*What kind of function the pane calls when clicked depends on the playingMode.
+        * Instead of checking what the playingMode is everytime a pane is clicked,
+        * we check it now and create different panes depending on the playingMode*/
+        if (gameState.greenPlayer==HUMAN && gameState.redPlayer==HUMAN) {
+            for (int i=0; i<BOARD_SIZE;i++){
+                for (int j=0; j<BOARD_SIZE; j++){
+                /*Creates the clickable panes of the board*/
+                    addPaneTwoPlayer(i,j);
+                    addPaneTwoPlayer(j,i);
+                }
+            }
+            /*Makes the first move*/
+            makeGUIPlacement("MMUA");
+        }
+        if (gameState.greenPlayer==HUMAN && gameState.redPlayer!=HUMAN) {
+            for (int i=0; i<BOARD_SIZE;i++){
+                for (int j=0; j<BOARD_SIZE; j++){
+                    addPanePlayerGreen(i,j);
+                    addPanePlayerGreen(j,i);
+                }
+            }
+            /*Makes the first move*/
+            makeGUIPlacement("MMUA");
+        }
+        if (gameState.greenPlayer!=HUMAN && gameState.redPlayer==HUMAN) {
+            for (int i=0; i<BOARD_SIZE;i++){
+                for (int j=0; j<BOARD_SIZE; j++){
+                /*Creates the clickable panes of the board*/
+                    addPanePlayerRed(i,j);
+                    addPanePlayerRed(j,i);
+                }
+            }
+            /*Makes the first move*/
+            makeGUIPlacement("MMUA");
+
+            /*Makes the opponent's move first*/
+            char redTile = (char) (playerR.available_tiles).get(playerR.used_tiles);
+            char greenTile = (char) (playerG.available_tiles).get(playerG.used_tiles);
+
+            String opponent = genMoveEasy(gameState.moveHistory, greenTile);
+            if (gameState.greenPlayer == MEDIUM) opponent =
+                    genMoveMedium(gameState.moveHistory, greenTile, redTile);
+            if (gameState.greenPlayer == HARD) opponent =
+                    generateMove(gameState.moveHistory, greenTile, redTile);
+
+            makeGUIPlacement(opponent);
+        }
+
+        /*Layout*/
+        clickablePanes.setLayoutX(offset);
+        clickablePanes.setLayoutY(offset);
+
+        /*The must be added in this order so the heights show on top of the tiles
+        * and the interactive panes are on top of all of them.*/
+        placementGrp.getChildren().addAll(thickBorder,playingBoard,heightLabels,clickablePanes);
+    }
+
+    /**
+     * The clickable panes for when there are two players
+     * Function by Zhixian Wu, Manal Mohania, and Joseph Meltzer.
+     * Idea of how to recursively creates panes that remember what position they were
+     * created for is from StackOverflow:
+     * <http://stackoverflow.com/questions/31095954/how-to-get-gridpane-row-and-column
+       -ids-on-mouse-entered-in-each-cell-of-grid-in>
+     * @param colIndex      The column the pane is on
+     * @param rowIndex      The row the pane is on
+     */
+    private void addPaneTwoPlayer(int colIndex, int rowIndex){
+        Pane pane = new Pane();
+        ImageView iv = new ImageView();
+
+        char col = (char) (colIndex+'A');
+        char row = (char) (rowIndex+'A');
+
+        /*Event by Zhixian Wu, this makes the player's move when they click on a pane*/
+        pane.setOnMouseClicked(event -> {
+                switch (gameState.playerTurn){
+                    case RED:
+                        String placement = String.valueOf(col) + row +
+                                (playerR.available_tiles).get(playerR.used_tiles) + playerR.rotation;
+                        makeGUIPlacement(placement);
+                        break;
+                    case GREEN:
+                        String placement2 = String.valueOf(col) + row +
+                                (playerG.available_tiles).get(playerG.used_tiles) + playerG.rotation;
+                        makeGUIPlacement(placement2);
+                        break;
+                    case BLACK:
+                        makeGUIPlacement("MMUA");
+                        break;
+                }
+
+        });
+
+        /*Event by Joseph Meltzer, rotates the piece on scrolling*/
+        pane.setOnScroll(event -> {
+            removeTempPlacement(iv);
+            if (gameState.playerTurn==GREEN) {
+                playerG.rotateTile();
+                if (event.getDeltaY()>0) {
+                    playerG.rotateTile(); playerG.rotateTile();
+                }
+                ivg.setRotate((((int) (playerG.rotation) - 'A') * 90));
+                iv.setRotate(((int) (playerG.rotation) - 'A') * 90);
+            }
+            else {
+                playerR.rotateTile();
+                if (event.getDeltaY()>0) {
+                    playerR.rotateTile(); playerR.rotateTile();
+                }
+                ivr.setRotate(((int) (playerR.rotation) - 'A') * 90);
+                iv.setRotate(((int) (playerR.rotation) - 'A') * 90);
+            }
+
+            switch (gameState.playerTurn){
+                case RED:
+                    String placement = "" + col + row +
+                            (playerR.available_tiles).get(playerR.used_tiles) + playerR.rotation;
+                    makeTempPlacement(iv, placement);
+                    break;
+                case GREEN:
+                    String placement2 = "" + col + row +
+                            (playerG.available_tiles).get(playerG.used_tiles) + playerG.rotation;
+                    makeTempPlacement(iv, placement2);
+                    break;
+            }
+        });
+
+        /*Event by Manal Mohania, this creates the preview piece*/
+        pane.setOnMouseEntered(event -> {
+
+            switch (gameState.playerTurn){
+                case RED:
+                    String placement = "" + col + row +
+                            (playerR.available_tiles).get(playerR.used_tiles) + playerR.rotation;
+                    makeTempPlacement(iv, placement);
+                    break;
+                case GREEN:
+                    String placement2 = "" + col + row +
+                            (playerG.available_tiles).get(playerG.used_tiles) + playerG.rotation;
+                    makeTempPlacement(iv, placement2);
+                    break;
+            }
+        });
+
+        /*Event by Manal Mohania, this removes the preview piece*/
+        pane.setOnMouseExited(event -> removeTempPlacement(iv));
+
+        clickablePanes.getChildren().add(pane);
+        GridPane.setRowIndex(pane,rowIndex);
+        GridPane.setColumnIndex(pane,colIndex);
+    }
+
+    /**
+     * The clickable panes for when the human player is Green
+     * Function by Zhixian Wu, Manal Mohania, and Joseph Meltzer.
+     * Idea of how to recursively creates panes that remember what position
+     * they were created for is from StackOverflow:
+     * <http://stackoverflow.com/questions/31095954/how-to-get-gridpane-row
+       -and-column-ids-on-mouse-entered-in-each-cell-of-grid-in>
+     * @param colIndex      The column the pane is on
+     * @param rowIndex      The row the pane is on
+     */
+    private void addPanePlayerGreen(int colIndex, int rowIndex){
+        Pane pane = new Pane();
+        ImageView iv = new ImageView();
+        char col = (char) (colIndex+'A');
+        char row = (char) (rowIndex+'A');
+
+        /*Event by Joseph Meltzer, rotates the piece on scrolling*/
+        pane.setOnScroll(event -> {
+            removeTempPlacement(iv);
+            playerG.rotateTile();
+            if (event.getDeltaY()>0) {
+                playerG.rotateTile(); playerG.rotateTile();
+            }
+            ivg.setRotate((((int) (playerG.rotation) - 'A') * 90));
+            iv.setRotate(((int) (playerG.rotation) - 'A') * 90);
+
+            String placement2 = "" + col + row +
+                    (playerG.available_tiles).get(playerG.used_tiles) + playerG.rotation;
+            makeTempPlacement(iv, placement2);
+            });
+
+        /*Event by Manal Mohania, this adds the preview piece*/
+        pane.setOnMouseEntered(event -> {
+
+            String placement = "" + col + row +
+                    playerG.available_tiles.get(playerG.used_tiles) + playerG.rotation;
+            makeTempPlacement(iv, placement);
+        });
+
+        /*Event by Manal Mohania, this removes the preview piece*/
+        pane.setOnMouseExited(event -> removeTempPlacement(iv));
+
+        /*Event by Zhixian Wu. This event makes the player's move when they press on a pane.*/
+        pane.setOnMousePressed(event -> {
+            String placement = String.valueOf(col) + row +
+                    (playerG.available_tiles).get(playerG.used_tiles) + playerG.rotation;
+            makeGUIPlacement(placement);
+
+            int length = gameState.moveHistory.length()-2;
+            /*This adds the image that suggests tha AI is thinking.
+            We only suggest the AI is thinking if it actually is, i.e. your
+            move was valid, i.e. if the last move in moveHistory was yours*/
+            if ('K'<=gameState.moveHistory.charAt(length) && gameState.moveHistory.charAt(length)<='T'){
+                aiThink.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
+                controls.getChildren().add(aiThink);
+                aiThink.setLayoutX(750);
+                aiThink.setLayoutY(450);
+            }
+        });
+
+        /*Event by Zhixian Wu. This event causes the AI to make its move when the mouse is released.*/
+        pane.setOnMouseReleased(event -> {
+            int length = gameState.moveHistory.length()-2;
+
+            /*The AI only makes its move if your move was valid, i.e. if the
+            last move in moveHistory was yours*/
+            if ('K'<=gameState.moveHistory.charAt(length) &&
+                    gameState.moveHistory.charAt(length)<='T'){
+                makeAIMove();
+            }
+        });
+
+        clickablePanes.getChildren().add(pane);
+        GridPane.setRowIndex(pane,rowIndex);
+        GridPane.setColumnIndex(pane,colIndex);
+    }
+
+    /**
+     * The clickable panes for when the human player is Red
+     * Function by Zhixian Wu, Manal Mohania, and Joseph Meltzer.
+     * Idea of how to recursively creates panes that remember what position they
+     * were created for is from StackOverflow (URL in the in the C-u5807060 originality statement)
+     * @param colIndex      The column the pane is on
+     * @param rowIndex      The row the pane is on
+     */
+    private void addPanePlayerRed(int colIndex, int rowIndex){
+        Pane pane = new Pane();
+        ImageView iv = new ImageView();
+        char col = (char) (colIndex+'A');
+        char row = (char) (rowIndex+'A');
+
+        /*Event by Joseph Meltzer, rotates the piece on scrolling*/
+        pane.setOnScroll(event -> {
+            removeTempPlacement(iv);
+            playerR.rotateTile();
+            if (event.getDeltaY()>0) {
+                playerR.rotateTile(); playerR.rotateTile();
+            }
+            ivr.setRotate(((int) (playerR.rotation) - 'A') * 90);
+            iv.setRotate(((int) (playerR.rotation) - 'A') * 90);
+
+            String placement = "" + col + row +
+                    (playerR.available_tiles).get(playerR.used_tiles) + playerR.rotation;
+            makeTempPlacement(iv, placement);
+        });
+
+        /*Event by Manal Mohania, the adds the preview piece*/
+        pane.setOnMouseEntered(event -> {
+            String placement = "" + col + row +
+                    (playerR.available_tiles).get(playerR.used_tiles) + playerR.rotation;
+            makeTempPlacement(iv, placement);
+        });
+
+        /*Event by Manal Mohania, this removes the preview piece*/
+        pane.setOnMouseExited(event -> removeTempPlacement(iv));
+
+        /*Event by Zhixian Wu. This event makes the player's move when they press on a pane.*/
+        pane.setOnMousePressed(event -> {
+            String placement = String.valueOf(col) + row +
+                    (playerR.available_tiles).get(playerR.used_tiles) + playerR.rotation;
+            makeGUIPlacement(placement);
+
+            int length = gameState.moveHistory.length()-2;
+            /*This adds the image that suggests tha AI is thinking.
+            We only suggest the AI is thinking if it actually is, i.e. your
+            move was valid, i.e. if the last move in moveHistory was yours*/
+            if ('A'<=gameState.moveHistory.charAt(length) &&
+                    gameState.moveHistory.charAt(length)<='J'){
+                aiThink.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
+                controls.getChildren().add(aiThink);
+                aiThink.setLayoutX(740);
+                aiThink.setLayoutY(450);
+            }
+
+        });
+
+        /*Event by Zhixian Wu. This event causes the AI to make its move when the mouse is released.*/
+        pane.setOnMouseReleased(event -> {
+            int length = gameState.moveHistory.length()-2;
+
+            /*The first two conditions check if your move was valid, by
+            checking if the last move in moveHistory was yours. The AI only
+            makes its move if your move was valid.
+            *  The last condition checks if you are out of tiles, so the AI
+            doesn't try to make a move you are out of tiles*/
+            if ('A'<=gameState.moveHistory.charAt(length) &&
+                    gameState.moveHistory.charAt(length)<='J' && gameState.moveHistory.length()<MAX_TILES*8){
+                makeAIMove();
+            } else{
+                controls.getChildren().remove(aiThink);
+            }
+
+        });
+
+        clickablePanes.getChildren().add(pane);
+        GridPane.setRowIndex(pane,rowIndex);
+        GridPane.setColumnIndex(pane,colIndex);
+    }
+
+    /**
+     * This function removes the temporary placement created due to mouseover (if any)
+     * Function by Manal Mohania
+     * @param iv        The preview image to be removed
+     */
+    private void removeTempPlacement(ImageView iv){
+        if (iv == null)
+            return;
+
+        playingBoard.getChildren().remove(iv);
+    }
+
+    /**
+     * This function
+     * 1. creates a temporary placement upon mouseover - the placement pieces are
+     *    of different opacity depending upon the validity of the placement
+     * 2. ensures that the individual piece does not lie outside the board when making the placement
+     * 3. removes error messages if a valid placement is reached
+     *
+     * @param iv          The preview image to be added
+     * @param placement   the placement string
+     *
+     * Function by Manal Mohania
+     * Minor edits by Joseph Meltzer
+   */
+
+    private void makeTempPlacement(ImageView iv, String placement){
+
+        /*remove error messages, if any*/
+        controls.getChildren().remove(errorMessage);
+
+        /*The following ensure that the piece does not fall out of the board,
+        * and thus they are not part of the preview*/
+        if ((placement.charAt(0) == 'Z') && ((placement.charAt(3) == 'A')
+                || (placement.charAt(3) == 'D'))) return;
+
+        if ((placement.charAt(0) == 'A') && ((placement.charAt(3) == 'B')
+                || (placement.charAt(3) == 'C'))) return;
+
+        if ((placement.charAt(1) == 'Z') && ((placement.charAt(3) == 'A')
+                || (placement.charAt(3) == 'B'))) return;
+
+        if ((placement.charAt(1) == 'A') && ((placement.charAt(3) == 'C')
+                || (placement.charAt(3) == 'D'))) return;
+
+        /*set image according to the validity of the placement*/
+        if (StratoGame.isPlacementValid(gameState.moveHistory.concat(placement))) {
+            iv.setImage(new Image(Viewer.class.getResource(URI_BASE +
+                    placement.charAt(2) + "_h.png").toString()));
+            iv.setOpacity(0.8);
+        }
+        else {
+            iv.setImage(new Image(Viewer.class.getResource(URI_BASE +
+                    placement.charAt(2) + "_hx.png").toString()));
+            iv.setOpacity(0.5);
+        }
+
+        /* set up the piece */
+        iv.setRotate((((int) placement.charAt(3)) - 'A') * 90);
+        iv.setFitWidth(TILE_SIZE * 2);
+        iv.setPreserveRatio(true);
+        iv.setSmooth(true);
+        iv.setCache(true);
+        GridPane.setHalignment(iv, HPos.CENTER);
+        GridPane.setValignment(iv, VPos.CENTER);
+        playingBoard.getChildren().add(iv);
+
+        GridPane.setRowSpan(iv, 2);
+        GridPane.setColumnSpan(iv, 2);
+
+        /* Ensure correct rotation and correct coordinates for the piece */
+        switch (placement.charAt(3)) {
+            case 'A':
+                GridPane.setColumnIndex(iv, placement.charAt(0) - 'A');
+                GridPane.setRowIndex(iv, placement.charAt(1) - 'A');
+                break;
+            case 'B':
+                GridPane.setColumnIndex(iv, placement.charAt(0) - 'A' - 1);
+                GridPane.setRowIndex(iv, placement.charAt(1) - 'A');
+                break;
+            case 'C':
+                GridPane.setColumnIndex(iv, placement.charAt(0) - 'A' - 1);
+                GridPane.setRowIndex(iv, placement.charAt(1) - 'A' - 1);
+                break;
+            case 'D':
+                GridPane.setColumnIndex(iv, placement.charAt(0) - 'A');
+                GridPane.setRowIndex(iv, placement.charAt(1) - 'A' - 1);
+                break;
+        }
+
+    }
+
+    /**
+     * The next function updates the score of the green and the red players.
+     *
+     * These functions were written by Manal Mohania
+     * Some minor edits by Joseph Meltzer and Zhixian Wu
+     * */
+    private void updateScores(){
+        String placement = gameState.moveHistory;
+        int score = StratoGame.getScoreForPlacement(placement, true);
+        greenScore.setText("" + score);
+        int offset = (Integer.toString(score)).length() * 15;
+        greenScore.setLayoutX(790-offset);
+
+        int score2 = StratoGame.getScoreForPlacement(placement, false);
+        redScore.setText("" + score2);
+        int offset2 = (Integer.toString(score2)).length() * 15;
+        redScore.setLayoutX(870 - offset2);
+    }
+
+    /**
+     * The method that makes a placement
+     * Function by Zhixian Wu
+     * @param placement     The placement string
+     */
+    private void makeGUIPlacement(String placement) {
+        /*Remove some messages if they are on screen*/
+        controls.getChildren().remove(errorMessage);
+        controls.getChildren().remove(aiThink);
+
+        String tempMove = gameState.moveHistory.concat(placement);
+        if (!StratoGame.isPlacementValid(tempMove)) { /*If the attempted move is invalid*/
+            errorMessage.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
+            controls.getChildren().add(errorMessage);
+            errorMessage.setLayoutX(740);
+            errorMessage.setLayoutY(450);
+        } else {
+            /*create the image that'll go on the board*/
+            ImageView iv1 = new ImageView();
+            iv1.setImage(new Image(Viewer.class.getResource(URI_BASE +
+                    placement.charAt(2) + "_b.png").toString()));
+            iv1.setRotate((((int) placement.charAt(3)) - 'A') * 90);
+            iv1.setFitWidth(TILE_SIZE * 2);
+            iv1.setPreserveRatio(true);
+            iv1.setSmooth(true);
+            iv1.setCache(true);
+            playingBoard.getChildren().add(iv1);
+                /*make sure it spans two rows and columns*/
+            GridPane.setRowSpan(iv1, 2);
+            GridPane.setColumnSpan(iv1, 2);
+               /*make sure it's centered*/
+            GridPane.setHalignment(iv1, HPos.CENTER);
+            GridPane.setValignment(iv1, VPos.CENTER);
+
+            /*Place the image, in the correct rotation, in the correct place on the board*/
+            switch (placement.charAt(3)) {
+                case 'A':
+                    GridPane.setColumnIndex(iv1, (((int) placement.charAt(0)) - 'A'));
+                    GridPane.setRowIndex(iv1, (((int) placement.charAt(1)) - 'A'));
+                    break;
+                case 'B':
+                    GridPane.setColumnIndex(iv1, (((int) placement.charAt(0)) - 'A' - 1));
+                    GridPane.setRowIndex(iv1, (((int) placement.charAt(1)) - 'A'));
+                    break;
+                case 'C':
+                    GridPane.setColumnIndex(iv1, (((int) placement.charAt(0)) - 'A' - 1));
+                    GridPane.setRowIndex(iv1, (((int) placement.charAt(1)) - 'A' - 1));
+                    break;
+                case 'D':
+                    GridPane.setColumnIndex(iv1, (((int) placement.charAt(0)) - 'A'));
+                    GridPane.setRowIndex(iv1, (((int) placement.charAt(1)) - 'A' - 1));
+                    break;
+            }
+            /*Update the string of all the placements so far*/
+            gameState.updateMoves(placement);
+
+            /*Update the heights we're supposed to display*/
+            displayHeights();
+
+            /*Update the scores displayed*/
+            updateScores();
+
+            if (soundOn) audio.play();
+
+            /*Update the top tiles shown on the control panel,
+            * whose turn it is, and whose turn is bolded.*/
+            switch (gameState.playerTurn) {
+                case RED:
+                    if (playerR.used_tiles<19){ /*If red still has tiles left*/
+                        /*Update the red player's tile index*/
+                        playerR.getNextTile();
+                        /*Update the top red tile shown*/
+                        ivr.setImage(new Image(Viewer.class.getResource(URI_BASE +
+                                (playerR.available_tiles).get(playerR.used_tiles) +
+                                ".png").toString()));
+                        ivr.setFitWidth(80);
+                        ivr.setPreserveRatio(true);
+                        ivr.setSmooth(true);
+                        ivr.setCache(true);
+                    } else{ /*If red does not still have tiles left, say they're our of tiles*/
+                        Text outoftiles = new Text("Out of\n tiles");
+                        outoftiles.setFont(Font.font("Arial", FontWeight.BOLD, 24));
+                        GridPane.setColumnIndex(outoftiles,1);
+                        GridPane.setRowIndex(outoftiles,0);
+                        playerControls.getChildren().remove(ivr);
+                        playerControls.getChildren().add(outoftiles);
+                        playerR.getNextTile();
+                    }
+                    /*Update whose turn is bolded, and which rotate button is greyed out.*/
+                    greentxt.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
+                    redtxt.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));
+                    rotateG.setDisable(false);
+                    rotateR.setDisable(true);
+                    break;
+                case GREEN:
+                    if (playerG.used_tiles<19){ /*If green still has tiles left*/
+                        /*Update the red player's tile index*/
+                        playerG.getNextTile();
+                        /*Update the top green tile shown*/
+                        ivg.setImage(new Image(Viewer.class.getResource(URI_BASE +
+                                (playerG.available_tiles).get(playerG.used_tiles) +
+                                ".png").toString()));
+                        ivg.setFitWidth(80);
+                        ivg.setPreserveRatio(true);
+                        ivg.setSmooth(true);
+                        ivg.setCache(true);
+                    } else{ /*If green does not still have tiles left, say they're out of tiles*/
+                        Text outoftiles = new Text("Out of\n tiles");
+                        outoftiles.setFont(Font.font("Arial", FontWeight.BOLD, 24));
+                        GridPane.setColumnIndex(outoftiles,0);
+                        GridPane.setRowIndex(outoftiles,0);
+                        playerControls.getChildren().remove(ivg);
+                        playerControls.getChildren().add(outoftiles);
+                        playerG.getNextTile();
+                    }
+                    /*Update whose turn is bolded, and which rotate button is greyed out.*/
+                    greentxt.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));
+                    redtxt.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
+                    rotateG.setDisable(true);
+                    rotateR.setDisable(false);
+                    break;
+                case BLACK:
+                    rotateG.setDisable(false);
+                    rotateR.setDisable(true);
+                    break;
+            }
+            /*Update whose turn it is*/
+            gameState.nextTurn();
+
+            /*Update the number of tiles left*/
+            updateTilesLeft();
+
+            /*Checks if the game is over. If it is, we display the winner.*/
+            if (gameState.moveHistory.length() > MAX_TILES*8) {
+                /*Get rid of the panes*/
+                clickablePanes.getChildren().clear();
+                /*Lower opacity of the board*/
+                placementGrp.setOpacity(0.5);
+                playingBoard.setOpacity(0.3);
+                heightLabels.setOpacity(0.3);
+                nextMove.setDisable(true);
+                /*If green wins*/
+                if (Scoring.getWinner(gameState.moveHistory)){
+                    Text score = new Text("Green Wins!");
+                    score.setStyle("-fx-stroke: black; -fx-stroke-width: 1");
+                    score.setFill(Color.GREEN);
+                    score.setFont(Font.font("Arial", FontWeight.BOLD, 36));
+                    popUp2.getChildren().clear(); /*get rid of the last game's result*/
+                    popUp2.getChildren().add(score);
+                    score.setLayoutX(265);
+                    score.setLayoutY(300);
+                    root.getChildren().add(popUp2);
+                } else{ /*if red wins*/
+                    Text score = new Text("Red Wins!");
+                    score.setFill(Color.RED);
+                    score.setFont(Font.font("Arial", FontWeight.BOLD, 36));
+                    score.setStyle("-fx-stroke: black; -fx-stroke-width: 1");
+                    popUp2.getChildren().clear(); /*get rid of the last game's result*/
+                    popUp2.getChildren().add(score);
+                    score.setLayoutX(270);
+                    score.setLayoutY(300);
+                    root.getChildren().add(popUp2);
+                }
+                greentxt.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));
+                redtxt.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));
+            }
+        }
+    }
+
+    /*Function by Zhixian Wu
+    * It makes a move for the AI, which plays as whichever player whose turn it is*/
+    private void makeAIMove(){
+        if (gameState.moveHistory.length()<=MAX_TILES*8){ /*Checks the game is not over*/
+
+            char greenTile = (char) (playerG.available_tiles).get(playerG.used_tiles);
+            char redTile = (char) (playerR.available_tiles).get(playerR.used_tiles);
+
+            String opponent = "";
+
+            if (gameState.playerTurn==GREEN){ /*if the current turn is green*/
+                switch (gameState.greenPlayer){
+                    case EASY:
+                        opponent = genMoveEasy(gameState.moveHistory, greenTile);
+                        break;
+                    case MEDIUM:
+                        opponent = genMoveMedium(gameState.moveHistory, greenTile, redTile);
+                        break;
+                    case HARD:
+                        opponent = generateMove(gameState.moveHistory, greenTile, redTile);
+                        break;
+                    case CHEATING:
+                        opponent = genMoveCheating(gameState.moveHistory, playerG, playerR);
+                        break;
+                }
+
+                makeGUIPlacement(opponent);
+            } else{ /*if the current turn is red*/
+                switch (gameState.redPlayer){
+                    case EASY:
+                        opponent = genMoveEasy(gameState.moveHistory, redTile);
+                        break;
+                    case MEDIUM:
+                        opponent = genMoveMedium(gameState.moveHistory, redTile, greenTile);
+                        break;
+                    case HARD:
+                        opponent = generateMove(gameState.moveHistory, redTile, greenTile);
+                        break;
+                    case CHEATING:
+                        opponent = genMoveCheating(gameState.moveHistory, playerR, playerG);
+                        break;
+                }
+                makeGUIPlacement(opponent);
+            }
+        }
+    }
+
+    /*Display the height at each position*/
+    /*Function by Zhixian Wu*/
+    private void displayHeights(){
+        /*Clear existing heights*/
+        heightLabels.getChildren().clear();
+        /*Make 2D array of the height at each position*/
+        int[][] heights = heightArray(gameState.moveHistory);
+        /*Recursively go through each tile and label its height*/
+        for (int i=0; i<BOARD_SIZE;i++){
+            for (int j=0; j<BOARD_SIZE; j++){
+                if (heights[i][j]>1){
+                    String tall = Integer.toString(heights[i][j]);
+                    Text label1 = new Text(tall);
+                    label1.setFill(Color.WHITE);
+                    label1.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
+                    heightLabels.getChildren().add(label1);
+                    GridPane.setRowIndex(label1,j);
+                    GridPane.setColumnIndex(label1,i);
+                    GridPane.setHalignment(label1, HPos.CENTER);
+                    GridPane.setValignment(label1, VPos.CENTER);
+                }
+            }
+        }
+    }

     @Override
     public void start(Stage primaryStage) throws Exception {
+        primaryStage.setTitle("Stratopolis");
+        primaryStage.setResizable(false);
+        primaryStage.getIcons().add(new Image((Viewer.class.getResource(URI_BASE +
+                "icon.png").toString())));
+        scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT, LIGHTGREY);
+
+        root.getChildren().add(controls);
+        root.getChildren().add(placementGrp);
+
+        initialSettings();
+
+        primaryStage.setScene(scene);
+        primaryStage.show();
+
+    }

+    public static void main(String[] args) {
+        launch(args);
     }
 }
diff -ru -x .git ../master/comp1140-ass2/src/comp1110/ass2/gui/Viewer.java comp1140-ass2/src/comp1110/ass2/gui/Viewer.java
--- ../master/comp1140-ass2/src/comp1110/ass2/gui/Viewer.java	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/src/comp1110/ass2/gui/Viewer.java	2016-10-15 14:00:01.000000000 +1100
@@ -8,8 +8,11 @@
 import javafx.scene.control.Button;
 import javafx.scene.control.Label;
 import javafx.scene.control.TextField;
-import javafx.scene.layout.HBox;
+import javafx.scene.image.Image;
+import javafx.scene.image.ImageView;
+import javafx.scene.layout.*;
 import javafx.stage.Stage;
+import comp1110.ass2.StratoGame;

 /**
  * A very simple viewer for piece placements in the link game.
@@ -17,7 +20,11 @@
  * NOTE: This class is separate from your main game class.  This
  * class does not play a game, it just illustrates various piece
  * placements.
+ *
+ * Code written by Zhixian Wu
  */
+
+
 public class Viewer extends Application {

     /* board layout */
@@ -28,19 +35,71 @@

     private final Group root = new Group();
     private final Group controls = new Group();
+    private final Group placementGrp = new Group();
     TextField textField;

-
     /**
      * Draw a placement in the window, removing any previously drawn one
      *
      * @param placement  A valid placement string
      */
+
     void makePlacement(String placement) {
-        // FIXME Task 5: implement the simple placement viewer
+        if (!StratoGame.isPlacementValid(placement)){
+            throw new IllegalArgumentException("Bad placement " + placement);
+        } else{
+            GridPane gridPane = new GridPane();
+            gridPane.setPrefSize(624, 624);
+            gridPane.setMaxSize(624, 624);
+
+            gridPane.setGridLinesVisible(true);
+            for (int i = 0; i < 26; i++) {
+                RowConstraints row = new RowConstraints(24);
+                gridPane.getRowConstraints().add(row);
+            }
+            for (int i = 0; i < 26; i++) {
+                ColumnConstraints column = new ColumnConstraints(24);
+                gridPane.getColumnConstraints().add(column);
+            }
+
+
+            for (int i=0; i<(placement.length()/4); i++){
+                ImageView iv1 = new ImageView();
+                iv1.setImage(new Image(Viewer.class.getResource(URI_BASE + placement.charAt(4*i+2) + ".png").toString()));
+                iv1.setRotate((((int) placement.charAt(4*i+3))-65)*90);
+                iv1.setFitWidth(48);
+                iv1.setPreserveRatio(true);
+                iv1.setSmooth(true);
+                iv1.setCache(true);
+                gridPane.getChildren().add(iv1);
+                GridPane.setRowSpan(iv1,2);
+                GridPane.setColumnSpan(iv1,2);
+                switch (placement.charAt(4*i+3)){
+                    case 'A':
+                        GridPane.setColumnIndex(iv1,(((int) placement.charAt(4*i))-65));
+                        GridPane.setRowIndex(iv1,(((int) placement.charAt(4*i+1))-65));
+                        break;
+                    case 'B':
+                        GridPane.setColumnIndex(iv1,(((int) placement.charAt(4*i))-65-1));
+                        GridPane.setRowIndex(iv1,(((int) placement.charAt(4*i+1))-65));
+                        break;
+                    case 'C':
+                        GridPane.setColumnIndex(iv1,(((int) placement.charAt(4*i))-65-1));
+                        GridPane.setRowIndex(iv1,(((int) placement.charAt(4*i+1))-65-1));
+                        break;
+                    case 'D':
+                        GridPane.setColumnIndex(iv1,(((int) placement.charAt(4*i))-65));
+                        GridPane.setRowIndex(iv1,(((int) placement.charAt(4*i+1))-65-1));
+                        break;
+                }
+            }
+            placementGrp.getChildren().add(gridPane);
+
+        }
     }


+
     /**
      * Create a basic text field for input and a refresh button.
      */
@@ -48,8 +107,9 @@
         Label label1 = new Label("Placement:");
         textField = new TextField ();
         textField.setPrefWidth(300);
-        Button button = new Button("Refresh");
+        Button button = new Button("Refresh"); /*creates the 'Refresh' button*/
         button.setOnAction(new EventHandler<ActionEvent>() {
+            /*the bit in these curly braces is the behaviour that is executed when the button is pressed*/
             @Override
             public void handle(ActionEvent e) {
                 makePlacement(textField.getText());
@@ -62,6 +122,19 @@
         hb.setLayoutX(130);
         hb.setLayoutY(VIEWER_HEIGHT - 50);
         controls.getChildren().add(hb);
+
+        // Week 7: we need to create an initial three buttons for three choices.
+        // 1. Once a button is pressed, the buttons are cleared, PlayerA and PlayerB generated, and their top tiles (index used_tiles
+        // of char-array available_tiles) and whose turn it is are displayed.
+        // 2. There should be a rotate button for the player who has the current turn
+          // (and this rotation is stored as part of the Player object)
+        // 3. Each box in the GridPane (in Viewer) would need to be a clickable event that concatenates
+          // its coords with the tile and the rotation
+        // 4. The move can then be passed to makePlacement
+        // 5. There needs to be a method to update BoardState (player turn, 7., etc.). The move should be passed to this with the current BoardState.
+        // 6. Maybe the entire string of moves so far can be stored as a member variable of BoardState to calculate the score later
+        // 7. Or just alter BoardState to have a [26][26] array of heights and top colour.
+          // (^ This would be easier to implement if you've done isPlacementValid())
     }

     @Override
@@ -70,6 +143,7 @@
         Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

         root.getChildren().add(controls);
+        root.getChildren().add(placementGrp);

         makeControls();

Only in comp1140-ass2/src/comp1110/ass2/gui/assets: A_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: A_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: A_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: B_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: B_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: B_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: C_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: C_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: C_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: D_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: D_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: D_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: E_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: E_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: E_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: F_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: F_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: F_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: G_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: G_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: G_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: H_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: H_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: H_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: I_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: I_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: I_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: J_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: J_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: J_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: K_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: K_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: K_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: L_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: L_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: L_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: M_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: M_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: M_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: N_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: N_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: N_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: O_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: O_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: O_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: P_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: P_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: P_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: Q_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: Q_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: Q_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: R_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: R_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: R_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: S_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: S_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: S_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: T_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: T_h.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: T_hx.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: U_b.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: icon.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: outoftiles.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: sound.mp3
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: sound.wav
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: sound_icon.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: sound_icon_off.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: stratopolis.png
Only in comp1140-ass2/tests/comp1110/ass2: AITestByJoseph.java
Only in comp1140-ass2/tests/comp1110/ass2: GameStateTest.java
diff -ru -x .git ../master/comp1140-ass2/tests/comp1110/ass2/GenerateMoveTest.java comp1140-ass2/tests/comp1110/ass2/GenerateMoveTest.java
--- ../master/comp1140-ass2/tests/comp1110/ass2/GenerateMoveTest.java	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/tests/comp1110/ass2/GenerateMoveTest.java	2016-10-15 14:00:01.000000000 +1100
@@ -17,7 +17,13 @@
  */
 public class GenerateMoveTest {

-    @Test(timeout=10000)
+    /**
+     * Note: This test has been altered by Joseph Meltzer
+     * generateMove has been replaced by genMoveMedium because this function is faster.
+     * The timeout has also been changed to 25 seconds.
+     * On Joseph's computer the test takes ~23 seconds to run.
+     */
+    @Test
     public void testMove() {

         /* first ensure that the game correctly identifies broken placements */
@@ -35,10 +41,10 @@

             String move;
             for (int i = 0; i < 20; i++) {
-                move = StratoGame.generateMove(game, green[i], red[i]);
+                move = StratoGame.genMoveMedium(game, green[i], red[i]);
                 checkMove(game, green[i], move);
                 game += move;
-                move = StratoGame.generateMove(game, red[i], (i < 19 ? green[i + 1] : 0));
+                move = StratoGame.genMoveMedium(game, red[i], (i < 19 ? green[i + 1] : 0));
                 checkMove(game, red[i], move);
                 game += move;
             }
Only in comp1140-ass2/tests/comp1110/ass2: PlayerTestByZhixian.java
diff -ru -x .git ../master/comp1140-ass2/tests/comp1110/ass2/ScoreFromPlacementTest.java comp1140-ass2/tests/comp1110/ass2/ScoreFromPlacementTest.java
--- ../master/comp1140-ass2/tests/comp1110/ass2/ScoreFromPlacementTest.java	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/tests/comp1110/ass2/ScoreFromPlacementTest.java	2016-10-15 14:00:01.000000000 +1100
@@ -21,10 +21,10 @@
         for (int i = 0; i < PLACEMENTS.length; i++) {
             score = StratoGame.getScoreForPlacement(PLACEMENTS[i], true);
             ref = SCORES[i*2];
-            assertTrue("Incorrect score for '"+PLACEMENTS[i]+"', expected "+ref+", but got "+score, score == ref);
+            assertTrue("Incorrect score for green'"+PLACEMENTS[i]+"', expected "+ref+", but got "+score, score == ref);
             score = StratoGame.getScoreForPlacement(PLACEMENTS[i], false);
             ref = SCORES[1+(i*2)];
-            assertTrue("Incorrect score for '"+PLACEMENTS[i]+"', expected "+ref+", but got "+score, score == ref);
+            assertTrue("Incorrect score for red'"+PLACEMENTS[i]+"', expected "+ref+", but got "+score, score == ref);
         }
     }
 }
Only in comp1140-ass2/tests/comp1110/ass2: ScoringAndWinning.java
```
## Test log
```
--javac output--
javac: file not found: comp1140-ass2/src/comp1140/ass2/*.java
Usage: javac <options> <source files>
use -help for a list of possible options
javac: file not found: comp1140-ass2/src/comp1140/ass2/gui/*.java
Usage: javac <options> <source files>
use -help for a list of possible options
----
java -cp comp1140-ass2/src:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/ass2-1140-tests.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/hamcrest-core-1.3.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/junit-4.12.jar org.junit.runner.JUnitCore comp1140.ass2.TilePlacementWellFormedTest
---
JUnit version 4.12
.E
Time: 0.002
There was 1 failure:
1) initializationError(org.junit.runner.JUnitCommandLineParseResult)
java.lang.IllegalArgumentException: Could not find class [comp1140.ass2.TilePlacementWellFormedTest]
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:102)
	at org.junit.runner.JUnitCommandLineParseResult.parseArgs(JUnitCommandLineParseResult.java:50)
	at org.junit.runner.JUnitCommandLineParseResult.parse(JUnitCommandLineParseResult.java:44)
	at org.junit.runner.JUnitCore.runMain(JUnitCore.java:72)
	at org.junit.runner.JUnitCore.main(JUnitCore.java:36)
Caused by: java.lang.ClassNotFoundException: comp1140.ass2.TilePlacementWellFormedTest
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at org.junit.internal.Classes.getClass(Classes.java:16)
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:100)
	... 4 more

FAILURES!!!
Tests run: 1,  Failures: 1

java -cp comp1140-ass2/src:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/ass2-1140-tests.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/hamcrest-core-1.3.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/junit-4.12.jar org.junit.runner.JUnitCore comp1140.ass2.PlacementWellFormedTest
---
JUnit version 4.12
.E
Time: 0.003
There was 1 failure:
1) initializationError(org.junit.runner.JUnitCommandLineParseResult)
java.lang.IllegalArgumentException: Could not find class [comp1140.ass2.PlacementWellFormedTest]
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:102)
	at org.junit.runner.JUnitCommandLineParseResult.parseArgs(JUnitCommandLineParseResult.java:50)
	at org.junit.runner.JUnitCommandLineParseResult.parse(JUnitCommandLineParseResult.java:44)
	at org.junit.runner.JUnitCore.runMain(JUnitCore.java:72)
	at org.junit.runner.JUnitCore.main(JUnitCore.java:36)
Caused by: java.lang.ClassNotFoundException: comp1140.ass2.PlacementWellFormedTest
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at org.junit.internal.Classes.getClass(Classes.java:16)
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:100)
	... 4 more

FAILURES!!!
Tests run: 1,  Failures: 1

java -cp comp1140-ass2/src:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/ass2-1140-tests.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/hamcrest-core-1.3.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/junit-4.12.jar org.junit.runner.JUnitCore comp1140.ass2.PlacementValidTest
---
JUnit version 4.12
.E
Time: 0.003
There was 1 failure:
1) initializationError(org.junit.runner.JUnitCommandLineParseResult)
java.lang.IllegalArgumentException: Could not find class [comp1140.ass2.PlacementValidTest]
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:102)
	at org.junit.runner.JUnitCommandLineParseResult.parseArgs(JUnitCommandLineParseResult.java:50)
	at org.junit.runner.JUnitCommandLineParseResult.parse(JUnitCommandLineParseResult.java:44)
	at org.junit.runner.JUnitCore.runMain(JUnitCore.java:72)
	at org.junit.runner.JUnitCore.main(JUnitCore.java:36)
Caused by: java.lang.ClassNotFoundException: comp1140.ass2.PlacementValidTest
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at org.junit.internal.Classes.getClass(Classes.java:16)
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:100)
	... 4 more

FAILURES!!!
Tests run: 1,  Failures: 1

java -cp comp1140-ass2/src:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/ass2-1140-tests.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/hamcrest-core-1.3.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/junit-4.12.jar org.junit.runner.JUnitCore comp1140.ass2.ScoreFromPlacementTest
---
JUnit version 4.12
.E
Time: 0.004
There was 1 failure:
1) initializationError(org.junit.runner.JUnitCommandLineParseResult)
java.lang.IllegalArgumentException: Could not find class [comp1140.ass2.ScoreFromPlacementTest]
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:102)
	at org.junit.runner.JUnitCommandLineParseResult.parseArgs(JUnitCommandLineParseResult.java:50)
	at org.junit.runner.JUnitCommandLineParseResult.parse(JUnitCommandLineParseResult.java:44)
	at org.junit.runner.JUnitCore.runMain(JUnitCore.java:72)
	at org.junit.runner.JUnitCore.main(JUnitCore.java:36)
Caused by: java.lang.ClassNotFoundException: comp1140.ass2.ScoreFromPlacementTest
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at org.junit.internal.Classes.getClass(Classes.java:16)
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:100)
	... 4 more

FAILURES!!!
Tests run: 1,  Failures: 1

java -cp comp1140-ass2/src:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/ass2-1140-tests.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/hamcrest-core-1.3.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/junit-4.12.jar org.junit.runner.JUnitCore comp1140.ass2.GenerateMoveTest
---
JUnit version 4.12
.E
Time: 0.002
There was 1 failure:
1) initializationError(org.junit.runner.JUnitCommandLineParseResult)
java.lang.IllegalArgumentException: Could not find class [comp1140.ass2.GenerateMoveTest]
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:102)
	at org.junit.runner.JUnitCommandLineParseResult.parseArgs(JUnitCommandLineParseResult.java:50)
	at org.junit.runner.JUnitCommandLineParseResult.parse(JUnitCommandLineParseResult.java:44)
	at org.junit.runner.JUnitCore.runMain(JUnitCore.java:72)
	at org.junit.runner.JUnitCore.main(JUnitCore.java:36)
Caused by: java.lang.ClassNotFoundException: comp1140.ass2.GenerateMoveTest
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at org.junit.internal.Classes.getClass(Classes.java:16)
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:100)
	... 4 more

FAILURES!!!
Tests run: 1,  Failures: 1

```
