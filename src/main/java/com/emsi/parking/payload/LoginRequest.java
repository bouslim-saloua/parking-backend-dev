/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emsi.parking.payload;

import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author bssal
 */


@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;
}

