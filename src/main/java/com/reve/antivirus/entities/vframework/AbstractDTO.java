package com.reve.antivirus.entities.vframework;


import reveantivirus.util.Serialize;

/**
 *
 * @author reve
 */
public abstract class AbstractDTO{
    @Override
    public String toString() {
        return Serialize.asJSON(this);
    }
}