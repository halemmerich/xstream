/*
 * Copyright (C) 2008 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 * 
 * Created on 28. October 2008 by Joerg Schaible
 */
package com.thoughtworks.acceptance;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * Tests with buffered images.
 * 
 * @author J&ouml;rg Schaible
 */
public class BufferedImagesTest extends AbstractAcceptanceTest {

    public void testInBWCanBeMarshalled() throws IOException {
        final BufferedImage image = new BufferedImage(3, 3, BufferedImage.TYPE_BYTE_BINARY);
        final Graphics2D graphics = image.createGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, 2, 2);
        graphics.setColor(Color.BLACK);
        graphics.drawLine(0, 0, 2, 2);

        final ByteArrayOutputStream baosOriginal = new ByteArrayOutputStream();
        ImageIO.write(image, "tiff", baosOriginal);

        xstream.alias("image", BufferedImage.class);
        final String xml = xstream.toXML(image);

        final ByteArrayOutputStream baosSerialized = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage)xstream.fromXML(xml), "tiff", baosSerialized);

        assertArrayEquals(baosOriginal.toByteArray(), baosSerialized.toByteArray());
    }

    public void testInRGBACanBeMarshalled() throws IOException {
        final BufferedImage image = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics = image.createGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, 2, 2);
        graphics.setColor(Color.RED);
        graphics.drawLine(0, 0, 2, 2);

        final ByteArrayOutputStream baosOriginal = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baosOriginal);

        xstream.alias("image", BufferedImage.class);
        final String xml = xstream.toXML(image);
        System.out.println(xml);

        final ByteArrayOutputStream baosSerialized = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage)xstream.fromXML(xml), "png", baosSerialized);

        assertArrayEquals(baosOriginal.toByteArray(), baosSerialized.toByteArray());
    }
}
