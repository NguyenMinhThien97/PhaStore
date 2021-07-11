import React from 'react'
import {
  CButton,
  CCard,
  CCardBody,
  CCardFooter,
  CCardHeader,
  CCol,
  CForm,
  CFormGroup,
  CInput,
  CInputGroup,
  CInputGroupPrepend,
  CInputGroupText,
  CSelect,
  CRow,
} from '@coreui/react'
import CIcon from '@coreui/icons-react'



const ClientInfo = () => {

  var curr = new Date();
  var today = curr.toISOString().substr(0,10);
  return (
    <>
      <CRow>
        <CCol xs="12">
          <CCard>
            <CCardHeader>
              Lưu thông tin khách hàng
            </CCardHeader>
            <CCardBody>
              <CForm action="" method="post">
                <CFormGroup row>
                  <CCol md="6">
                    <CInputGroup>
                      <CInputGroupPrepend>
                        <CInputGroupText><CIcon name="cil-user" /></CInputGroupText>
                      </CInputGroupPrepend>
                      <CInput id="userName" name="userName" placeholder="Tên đăng nhập" autoComplete="name"/>
                    </CInputGroup>
                  </CCol>
                  <CCol md="6">
                    <CInputGroup>
                      <CInputGroupPrepend>
                        <CInputGroupText><CIcon name="cil-envelope-closed" /></CInputGroupText>
                      </CInputGroupPrepend>
                      <CInput id="email" name="email" placeholder="Email" autoComplete="email"/>
                    </CInputGroup>
                  </CCol>
                </CFormGroup>

                <CFormGroup row>
                  <CCol md="6">
                    <CInputGroup>
                      <CInputGroupPrepend>
                        <CInputGroupText><CIcon name="cil-user" /></CInputGroupText>
                      </CInputGroupPrepend>
                      <CInput id="lastName" name="lastName" placeholder="Họ" autoComplete="name"/>
                    </CInputGroup>
                  </CCol>
                  <CCol md="6">
                    <CInputGroup>
                      <CInputGroupPrepend>
                        <CInputGroupText><CIcon name="cil-user" /></CInputGroupText>
                      </CInputGroupPrepend>
                      <CInput id="firstName" name="firstName" placeholder="Tên" autoComplete="name"/>
                    </CInputGroup>
                  </CCol>
                </CFormGroup>

                <CFormGroup row>
                  <CCol md="6">
                    <CInputGroup>
                      <CInputGroupPrepend>
                        <CInputGroupText>Tên công ty</CInputGroupText>
                      </CInputGroupPrepend>
                      <CSelect custom name="select" id="select">
                        <option value="0">Chọn công ty...</option>
                        <option value="1">Option #1</option>
                        <option value="2">Option #2</option>
                        <option value="3">Option #3</option>
                      </CSelect>
                    </CInputGroup>
                  </CCol>
                  <CCol md="6">
                    <CInputGroup>
                      <CInputGroupPrepend>
                        <CInputGroupText>Ngày Sinh</CInputGroupText>
                      </CInputGroupPrepend>
                      <CInput type="date" id="birthday" name="birthday"/>
                    </CInputGroup>
                  </CCol>
                </CFormGroup>

                <CFormGroup row>
                  <CCol md="6">
                    <CInputGroup>
                      <CInputGroupPrepend>
                        <CInputGroupText><CIcon name="cil-phone" /></CInputGroupText>
                      </CInputGroupPrepend>
                      <CInput id="phoneNumber " name="phoneNumber" placeholder="Số điện thoại"/>
                    </CInputGroup>
                  </CCol>
                  <CCol md="6">
                    <CInputGroup>
                      <CInputGroupPrepend>
                        <CInputGroupText><CIcon name="cil-map" /></CInputGroupText>
                      </CInputGroupPrepend>
                      <CInput id="address" name="address" placeholder="Địa chỉ"/>
                    </CInputGroup>
                  </CCol>
                </CFormGroup>

                <CFormGroup row>
                  <CCol md="6">
                    <CInputGroup>
                      <CInputGroupPrepend>
                        <CInputGroupText><CIcon name="cil-check" /></CInputGroupText>
                      </CInputGroupPrepend>
                      <CSelect custom name="select" id="select">
                        <option value="0">Tài khoản bị khóa</option>
                        <option value="1" defaultValue>Đang hoạt động</option>
                      </CSelect>
                    </CInputGroup>
                  </CCol>
                  <CCol md="6">
                    <CInputGroup>
                      <CInputGroupPrepend>
                        <CInputGroupText>Ngày tạo</CInputGroupText>
                      </CInputGroupPrepend>
                      <CInput type="date" id="CreatedAt" name="CreatedAt" defaultValue={today} disabled/>
                    </CInputGroup>
                  </CCol>
                </CFormGroup>

              </CForm>
            </CCardBody>
            <CCardFooter className="text-center">
              <CButton type="submit" size="sm" color="primary"><CIcon name="cil-scrubber" /> Lưu</CButton> <CButton type="reset" size="sm" color="danger"><CIcon name="cil-ban" /> Hủy Lưu</CButton>
            </CCardFooter>
          </CCard>
        </CCol>
      </CRow>
    </>
  )
}

export default ClientInfo

