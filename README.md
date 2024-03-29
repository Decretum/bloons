# bloons
I messed around with LibGDX and made my own version of Bloons Tower Defense, the browser/mobile game from Ninja Kiwi.
To run the game, you really only have to download and run "game.jar". Alternatively, you can download the entire project and build it yourself, and then run that.
I've only tested this game on Windows so far, so if the game doesn't yet work on Mac or Linux I wouldn't really be surprised.

![](bloons3.gif)

I was reading up on examples from https://www.gamefromscratch.com/page/LibGDX-Tutorial-series.aspx, which is a little dated but otherwise fine. There was a lot of googling for very specific questions as well.
I was using https://www.iloveimg.com/crop-image/crop-png since Microsoft paint doesn't seem to let me crop with a transparent background.
The image assets were mostly found on the bloons tower defense wiki, or from the Touhou game Imperishable Night by ZUN.

What's new: Put down Reimu or Yuyuko, have her selected, and press X and she will use her spell card!

![](yuyuko_spellcard.gif)

Ideas that are not yet implemented that I would like to implement:

- More spell cards (ultimate attacks) for towers that have a separate cooldown (partially implemented)
-- spell cards might need a direction and rotation of their own (not Reimu's or Yukari's though)
-- some spell card attacks involve very large static bullets, you need to make those expire without the conventional distance travelled metric
-- spell cards for Yukari, Marisa, Alice, Sakuya, Remilia, Youmu
-- Maybe Youmu's spell card will involve her moving around the map? Maybe.