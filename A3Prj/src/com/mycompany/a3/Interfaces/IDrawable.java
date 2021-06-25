package com.mycompany.a3.Interfaces;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameObjects.GameObject;

public interface IDrawable {

    public void draw(Graphics g, Point pCmpRelPrnt);

}