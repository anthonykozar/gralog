/* This file is part of Gralog, Copyright (c) 2016-2018 LaS group, TU Berlin.
 * License: https://www.gnu.org/licenses/gpl.html GPL version 3 or later. */
package gralog.chipfiring.structure;

import gralog.plugins.XmlName;
import gralog.preferences.Configuration;
import gralog.structure.*;

@XmlName(name = "chipfiringgamemove")
public class ChipFiringGameMove extends Edge {

    public ChipFiringGameMove() {

    }

    public ChipFiringGameMove(Configuration config) {
        super(config);
    }

}
