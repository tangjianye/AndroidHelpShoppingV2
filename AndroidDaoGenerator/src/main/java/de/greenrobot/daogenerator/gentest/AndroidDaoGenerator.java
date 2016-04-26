package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class AndroidDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.product.colorfulnote.db.gen");

        addNote(schema);
        new DaoGenerator().generateAll(schema, "colorfulnote/src/main/java");
    }

    private static void addNote(Schema schema) {
        Entity entity = schema.addEntity("Note");
        entity.addIdProperty().autoincrement();
        entity.addStringProperty("title").notNull();
        entity.addStringProperty("content").notNull();
        entity.addDateProperty("date").notNull();
    }
}
