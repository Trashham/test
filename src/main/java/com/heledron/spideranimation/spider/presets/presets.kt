package com.heledron.spideranimation.spider.presets

import com.heledron.spideranimation.spider.configuration.BodyPlan
import com.heledron.spideranimation.spider.configuration.SegmentPlan
import com.heledron.spideranimation.utilities.FORWARD_VECTOR
import org.bukkit.util.Vector

private const val rootThickness = 1.0/16 * 4.5
private const val tipThickness = 1.0/16 * 1.5

private fun createTaperedSegments(segmentCount: Int, length: Double): List<SegmentPlan> {
    return SegmentPlan.tapered(segmentCount, length, rootThickness, tipThickness)
}

fun bipedBodyPlan(segmentCount: Int, segmentLength: Double): BodyPlan {
    return BodyPlan().apply {


        addLegPair(Vector(.0, .0, .0), Vector(1.0, .0, .0), createTaperedSegments(segmentCount, 1.0 * segmentLength))
    }
}

fun quadrupedBodyPlan(segmentCount: Int, segmentLength: Double): BodyPlan {
    return BodyPlan().apply {
        addLegPair(Vector(.0, .0, .0), Vector(0.9,.0, 0.9), createTaperedSegments(segmentCount, 0.9 * segmentLength))
        addLegPair(Vector(.0, .0, .0), Vector(1.0, .0, -1.1), createTaperedSegments(segmentCount, 1.2 * segmentLength))
    }
}

fun hexapodBodyPlan(segmentCount: Int, segmentLength: Double): BodyPlan {
    return BodyPlan().apply {
        addLegPair(Vector(.0,.0,0.1), Vector(1.0,.0, 1.1), createTaperedSegments(segmentCount, 1.1 * segmentLength))
        addLegPair(Vector(.0,.0,0.0), Vector(1.3,.0,-0.3), createTaperedSegments(segmentCount, 1.1 * segmentLength))
        addLegPair(Vector(.0,.0,-.1), Vector(1.2,.0,-2.0), createTaperedSegments(segmentCount, 1.6 * segmentLength))
    }
}

fun octopodBodyPlan(segmentCount: Int, segmentLength: Double): BodyPlan {
    return BodyPlan().apply {
        addLegPair(Vector(.0,.0,  .1), Vector(1.0, .0,  1.6), createTaperedSegments(segmentCount, 1.1 * segmentLength))
        addLegPair(Vector(.0,.0,  .0), Vector(1.3, .0,  0.4), createTaperedSegments(segmentCount, 1.0 * segmentLength))
        addLegPair(Vector(.0,.0, -.1), Vector(1.3, .0, -0.9), createTaperedSegments(segmentCount, 1.1 * segmentLength))
        addLegPair(Vector(.0,.0, -.2), Vector(1.1, .0, -2.5), createTaperedSegments(segmentCount, 1.6 * segmentLength))
    }
}


private fun createRobotSegments(lengthScale: Double) = List(4) { index ->
    var length = lengthScale.toFloat()
    var initDirection = FORWARD_VECTOR

    if (index == 0) {
        length *= .5f
        initDirection = initDirection.rotateAroundX(Math.PI / 3)
    }

    if (index == 1) {
        length *= .8f
    }

    val model = if (index == 0) {
        SpiderLegModel.BASE.clone().scale(1f,1f,length)
    } else if (index == 1) {
        SpiderLegModel.FEMUR.clone().scale(1f,1f,length)
    } else if (index == 2) {
        SpiderLegModel.TIBIA.clone().scale(1f,1f,length)
    } else {
        SpiderLegModel.TIP.clone().scale(1f,1f,length)
    }


    SegmentPlan(length.toDouble(), initDirection, model)
}


fun quadBotBodyPlan(_segmentCount: Int, segmentLength: Double): BodyPlan {
    return BodyPlan().apply {
        bodyModel = SpiderTorsoModels.FLAT.model.clone()
        addLegPair(root = Vector(.2,-.2 - .15, .2), rest = Vector(1.3 * 1.0,.0, 1.0), createRobotSegments(.9 * .7 * segmentLength))
        addLegPair(root = Vector(.2,-.2 - .15,-.2), rest = Vector(1.3 * 1.1,.0,-1.2), createRobotSegments(1.2 * .7 * segmentLength))
    }
}

fun hexBotBodyPlan(_segmentCount: Int, segmentLength: Double): BodyPlan {
    return BodyPlan().apply {
        bodyModel = SpiderTorsoModels.FLAT.model.clone()
        addLegPair(root = Vector(.2,-.2 - .15, .2), rest = Vector(1.3 * 1.0,.0, 1.3), createRobotSegments(1.1 * .7 * segmentLength))
        addLegPair(root = Vector(.2,-.2 - .15, .0), rest = Vector(1.3 * 1.2,.0,-0.1), createRobotSegments(1.1 * .7 * segmentLength))
        addLegPair(root = Vector(.2,-.2 - .15,-.2), rest = Vector(1.3 * 1.1,.0,-1.6), createRobotSegments(1.3 * .7 * segmentLength))
    }
}

fun octoBotBodyPlan(_segmentCount: Int, segmentLength: Double): BodyPlan {
    return BodyPlan().apply {
        bodyModel = SpiderTorsoModels.FLAT.model.clone()
        addLegPair(root = Vector(.2,-.2 - .15, .3), rest = Vector(1.3 * 1.0,.0, 1.3), createRobotSegments(1.1 * .7 * segmentLength))
        addLegPair(root = Vector(.2,-.2 - .15, .1), rest = Vector(1.3 * 1.2,.0, 0.5), createRobotSegments(1.0 * .7 * segmentLength))
        addLegPair(root = Vector(.2,-.2 - .15, .1), rest = Vector(1.3 * 1.2,.0,-0.7), createRobotSegments(1.1 * .7 * segmentLength))
        addLegPair(root = Vector(.2,-.2 - .15,-.3), rest = Vector(1.3 * 1.1,.0,-1.6), createRobotSegments(1.3 * .7 * segmentLength))
    }
}