package com.mk.po;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {
    private int user_id;
    private String user_code;
    private String user_name;
    private String user_password;
    private int user_state;

}
