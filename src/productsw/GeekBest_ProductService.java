

/**
 * GeekBest_ProductService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.3  Built on : May 30, 2016 (04:08:57 BST)
 */

    package productsw;

    /*
     *  GeekBest_ProductService java interface
     */

    public interface GeekBest_ProductService {
          

        /**
          * Auto generated method signature
          * 
                    * @param anadirNuevoProducto0
                
         */

         
                     public productsw.AnadirNuevoProductoResponse anadirNuevoProducto(

                        productsw.AnadirNuevoProducto anadirNuevoProducto0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param anadirNuevoProducto0
            
          */
        public void startanadirNuevoProducto(

            productsw.AnadirNuevoProducto anadirNuevoProducto0,

            final productsw.GeekBest_ProductServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param borrarProducto2
                
         */

         
                     public productsw.BorrarProductoResponse borrarProducto(

                        productsw.BorrarProducto borrarProducto2)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param borrarProducto2
            
          */
        public void startborrarProducto(

            productsw.BorrarProducto borrarProducto2,

            final productsw.GeekBest_ProductServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param borrarProductos4
                
         */

         
                     public productsw.BorrarProductosResponse borrarProductos(

                        productsw.BorrarProductos borrarProductos4)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param borrarProductos4
            
          */
        public void startborrarProductos(

            productsw.BorrarProductos borrarProductos4,

            final productsw.GeekBest_ProductServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param obtenerProductosProveedor6
                
         */

         
                     public productsw.ObtenerProductosProveedorResponse obtenerProductosProveedor(

                        productsw.ObtenerProductosProveedor obtenerProductosProveedor6)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param obtenerProductosProveedor6
            
          */
        public void startobtenerProductosProveedor(

            productsw.ObtenerProductosProveedor obtenerProductosProveedor6,

            final productsw.GeekBest_ProductServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param login8
                
         */

         
                     public productsw.LoginResponse login(

                        productsw.Login login8)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param login8
            
          */
        public void startlogin(

            productsw.Login login8,

            final productsw.GeekBest_ProductServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param editarProducto10
                
         */

         
                     public productsw.EditarProductoResponse editarProducto(

                        productsw.EditarProducto editarProducto10)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param editarProducto10
            
          */
        public void starteditarProducto(

            productsw.EditarProducto editarProducto10,

            final productsw.GeekBest_ProductServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  main(
         productsw.Main main12

        ) throws java.rmi.RemoteException
        
        ;

        

        /**
          * Auto generated method signature
          * 
                    * @param obtenerNumeroActualProductos13
                
         */

         
                     public productsw.ObtenerNumeroActualProductosResponse obtenerNumeroActualProductos(

                        productsw.ObtenerNumeroActualProductos obtenerNumeroActualProductos13)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param obtenerNumeroActualProductos13
            
          */
        public void startobtenerNumeroActualProductos(

            productsw.ObtenerNumeroActualProductos obtenerNumeroActualProductos13,

            final productsw.GeekBest_ProductServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    