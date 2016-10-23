
/**
 * GeekBest_ProductServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.3  Built on : May 30, 2016 (04:08:57 BST)
 */

    package productsw;

    /**
     *  GeekBest_ProductServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class GeekBest_ProductServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public GeekBest_ProductServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public GeekBest_ProductServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for anadirNuevoProducto method
            * override this method for handling normal response from anadirNuevoProducto operation
            */
           public void receiveResultanadirNuevoProducto(
                    productsw.AnadirNuevoProductoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from anadirNuevoProducto operation
           */
            public void receiveErroranadirNuevoProducto(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for borrarProducto method
            * override this method for handling normal response from borrarProducto operation
            */
           public void receiveResultborrarProducto(
                    productsw.BorrarProductoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from borrarProducto operation
           */
            public void receiveErrorborrarProducto(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for borrarProductos method
            * override this method for handling normal response from borrarProductos operation
            */
           public void receiveResultborrarProductos(
                    productsw.BorrarProductosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from borrarProductos operation
           */
            public void receiveErrorborrarProductos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerProductosProveedor method
            * override this method for handling normal response from obtenerProductosProveedor operation
            */
           public void receiveResultobtenerProductosProveedor(
                    productsw.ObtenerProductosProveedorResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerProductosProveedor operation
           */
            public void receiveErrorobtenerProductosProveedor(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for login method
            * override this method for handling normal response from login operation
            */
           public void receiveResultlogin(
                    productsw.LoginResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from login operation
           */
            public void receiveErrorlogin(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for editarProducto method
            * override this method for handling normal response from editarProducto operation
            */
           public void receiveResulteditarProducto(
                    productsw.EditarProductoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from editarProducto operation
           */
            public void receiveErroreditarProducto(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for obtenerNumeroActualProductos method
            * override this method for handling normal response from obtenerNumeroActualProductos operation
            */
           public void receiveResultobtenerNumeroActualProductos(
                    productsw.ObtenerNumeroActualProductosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerNumeroActualProductos operation
           */
            public void receiveErrorobtenerNumeroActualProductos(java.lang.Exception e) {
            }
                


    }
    