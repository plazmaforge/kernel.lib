/*
 * Copyright (C) 2012-2023 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package plazma.lib;

import java.io.Serializable;

public class ArithmeticContext implements Serializable {

    public static final NullMode DEFAULT_NULL_MODE = NullMode.NULL;

    public static final OverflowMode DEFAULT_OVERFLOW_MODE = OverflowMode.SHIFT;

    // TODO: Maybe rename 'NullMode' to 'NullOperatorMode'
    // TODO: Maybe rename 'OverflowMode' to 'OverflowNumberMode'
    // TODO: IndexRangeMode
    // TODO: NullOrderMode (FirstNull, LastNull)

    private static final long serialVersionUID = -6847568465886076749L;

    private NullMode nullMode;

    private OverflowMode overflowMode;

    public ArithmeticContext() {
        super();
        nullMode = DEFAULT_NULL_MODE;
        overflowMode = DEFAULT_OVERFLOW_MODE;
    }

    public ArithmeticContext(NullMode nullMode, OverflowMode overflowMode) {
        super();
        this.nullMode = nullMode == null ? DEFAULT_NULL_MODE : nullMode;
        this.overflowMode = overflowMode == null ? DEFAULT_OVERFLOW_MODE : overflowMode;
    }

    public ArithmeticContext(NullMode nullMode) {
        this(nullMode, null);
    }

    public ArithmeticContext(OverflowMode overflowMode) {
        this(null, overflowMode);
    }

    public NullMode getNullMode() {
        return nullMode;
    }

    public OverflowMode getOverflowMode() {
        return overflowMode;
    }

    public boolean isNullException() {
        return nullMode == NullMode.EXCEPTION;
    }

    public boolean isOverflowException() {
        return overflowMode == OverflowMode.EXCEPTION;
    }

}
