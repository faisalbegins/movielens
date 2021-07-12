package edu.miu.movielens;

import edu.miu.movielens.model.*;

import java.util.ArrayList;
import java.util.List;

public class LookupFactory {
    static List<Person> viewers() {
        List<Person> list = new ArrayList<>();
        // 1
        list.add(new Person() {{
            setName("Faisal Ahmed");
            setRole(new Viewer());
        }});
        // 2
        list.add(new Person() {{
            setName("Mustafizur Rahman Hilaly");
            setRole(new Viewer());
        }});
        // 3
        list.add(new Person() {{
            setName("Chandan Singh");
            setRole(new Viewer());
        }});
        // 4
        list.add(new Person() {{
            setName("Gopal Kunwar");
            setRole(new Viewer());
        }});
        // 5
        list.add(new Person() {{
            setName("Oyunbold Ganbold");
            setRole(new Viewer());
        }});
        return list;
    }

    static List<Person> writers() {
        List<Person> list = new ArrayList<>();
        // 1
        list.add(new Person() {{
            setName("Quentin Tarantino");
            setRole(new Writer());
        }});
        // 2
        list.add(new Person() {{
            setName("Francis Ford Coppola");
            setRole(new Writer());
        }});
        // 3
        list.add(new Person() {{
            setName("Christopher Nolan");
            setRole(new Writer());
        }});
        // 4
        list.add(new Person() {{
            setName("Woody Allen");
            setRole(new Writer());
        }});
        // 5
        list.add(new Person() {{
            setName("George Lucas");
            setRole(new Writer());
        }});
        // 6
        list.add(new Person() {{
            setName("James Cameron");
            setRole(new Writer());
        }});
        return list;
    }

    static List<Person> actors() {
        List<Person> list = new ArrayList<>();
        // 1
        list.add(new Person() {{
            setName("John David Washington");
            setRole(new Actor());
        }});
        // 2
        list.add(new Person() {{
            setName("Robert Pattinson");
            setRole(new Actor());
        }});
        // 3
        list.add(new Person() {{
            setName("Matthew McConaughey");
            setRole(new Actor());
        }});
        // 4
        list.add(new Person() {{
            setName("Anne Hathway");
            setRole(new Actor());
        }});
        // 5
        list.add(new Person() {{
            setName("Denzel Washington");
            setRole(new Actor());
        }});
        return list;
    }

    static List<Person> directors() {
        List<Person> list = new ArrayList<>();
        // 1
        list.add(new Person() {{
            setName("Quentin Tarantino");
            setRole(new Director());
        }});
        // 2
        list.add(new Person() {{
            setName("James Cameron");
            setRole(new Director());
        }});
        // 3
        list.add(new Person() {{
            setName("Christopher Nolan");
            setRole(new Director());
        }});
        // 4
        list.add(new Person() {{
            setName("Martin Scorsese");
            setRole(new Director());
        }});
        // 5
        list.add(new Person() {{
            setName("Steven Spielberg");
            setRole(new Director());
        }});
        return list;
    }

    static List<Award> awards() {
        List<Award> list = new ArrayList<>();
        list.add(new Award() {{
            setName("Oscars");
        }});
        list.add(new Award() {{
            setName("Grammy");
        }});
        return list;
    }
}
