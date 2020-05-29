package homework;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 14:25
 */
@Table("t_user")
public class User {
    @Column(name = "no",type = "int")
    private int id;

    @Column(name = "username",type = "varchar(20)")
    private String username;

    @Column(name = "pwd",type = "char(6)")
    private String password;

    @Column(name = "email",type = "varchar(50)")
    private String email;
}
