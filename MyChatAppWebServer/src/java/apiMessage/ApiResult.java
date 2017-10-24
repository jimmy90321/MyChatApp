/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apiMessage;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author user
 */
@Entity
public class ApiResult implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer _ErrCode;
    
    public Integer getErrCode() {
        return _ErrCode;
    }

    public void setErrCode(Integer ErrCode) {
        this._ErrCode = ErrCode;
    }
    
    private String _ErrMsg;
    public String getErrMsg() {
        return _ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this._ErrMsg = ErrMsg;
    }
    private String _data;

    public String getData() {
        return _data;
    }

    public void setData(String _data) {
        this._data = _data;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        return true;
    }

    @Override
    public String toString() {
        return "apiMessage.ApiResult[ id= ]";
    }
    
}
