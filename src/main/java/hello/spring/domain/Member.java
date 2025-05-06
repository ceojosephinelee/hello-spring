package hello.spring.domain;

public class Member {
    private Long id;
    private String name;

    /*@Override
    public String toString() {
        return name;
    }//로그 결과값에만 영향 줌-->나중에 더 공부*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
