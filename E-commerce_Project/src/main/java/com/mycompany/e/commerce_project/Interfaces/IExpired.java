/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.e.commerce_project.Interfaces;

import java.time.LocalDate;

/**
 *
 * @author TheGenius
 */
public interface IExpired {
    LocalDate getExpireDate();
    boolean isExpired();
}
