package sysseguridad.entidadesdenegocio;
public class Machine extends Usuario{
    
    /**
     * @param Object user es clase hija de Machine y es utilizada para traer el atributo nombre desde la otra clase
     * @param protected define que sera un atributo protegido que no cualquier clase podra acceder a el
     * @solo las clases hijas o heredadas, si en dado caso no se pueden guardar datos es necesario cambiar los protected a
     * @param private para poder accceder sin menos restricciones
     */
    
    Usuario user = new Usuario();
    
    protected int Id_Machines;
    protected String Machines_Name;
    protected String Brand;
    protected int Serial_Number;
    protected String Acquisition_Date;
    protected String Maintenance_Date;
    protected String Next_Maintenance_Date;
    protected byte Estatus;
    protected String NameUser = user.getNombre();
    
    /**
     *@param public define el constructor para que pudea ser utilizado en otras clases xd
     *@param metodos se le pasan para que puedan ser ingresados los datos desde otra clase
     * @since Machine se agrego NameUser que almacena el parametro de getNombre desde la otra clase.
     */
    
    public Machine(int Id_Machines, String Machines_Name, String Brand, int Serial_Number, String Acquisition_Date, String Maintenance_Date, String Next_Maintenance_Date, Byte Estatus, String NameUser){
        this.Id_Machines = Id_Machines;
        this.Machines_Name = Machines_Name;
        this.Brand = Brand;
        this.Serial_Number = Serial_Number;
        this.Acquisition_Date = Acquisition_Date;
        this.Maintenance_Date = Maintenance_Date;
        this.Next_Maintenance_Date = Next_Maintenance_Date;
        this.Estatus = Estatus;
        this.NameUser = NameUser;
    }
    
    /**
     * @param void no retorna nada, solo almacena en la variable
     * @param setters almacenas los datos en los atributos
     * @param getters obtienen los datos que se almacenaron anterior mente en los setters
     * esta madre es encapsulamiento
     */
    
    public void setIdMachines(int Id_Machines){
        this.Id_Machines = Id_Machines;
    }
    public int getIdMachines(){
        return Id_Machines;
    }
    
    public void setMachinesName(String Machines_Name){
        this.Machines_Name = Machines_Name;
    }
    public String getMachinesName(){
        return Machines_Name;
    }
    
    public void setBrand(String Brand){
        this.Brand = Brand;
    }
    public String getBrand(){
        return Brand;
    }
    
    public void setSerialNumber(int Serial_Number){
        this.Serial_Number = Serial_Number;
    }
    public int getSerialNumber(){
        return Serial_Number;
    }
    
    public void setAcquisitionDate(String Acquisition_Date){
        this.Acquisition_Date = Acquisition_Date;
    }
    public String getAcquisitionDate(){
        return Acquisition_Date;
    }
    
    public void setMaintenanceDate(String Maintenance_Date){
        this.Maintenance_Date = Maintenance_Date;
    }
    public String getMaintenanceDate(){
        return Maintenance_Date;
    }
    
    public void setNextMaintenanceDate(String Next_Maintenance_Date){
        this.Next_Maintenance_Date = Next_Maintenance_Date;
    }
    public String getNextMaintenanceDate(){
        return Next_Maintenance_Date;
    }
    
    public void setStatus(byte Status){
        this.Estatus = Status;
    }
    public byte getStatus(){
        return Estatus;
    }
 
    /**
     *@param setNameUser probalemente sea eliminada ya que no es necesario utlizarlo aqui, ya no agregaremos nombres desde machines
     * eso ya lo hace desde la clase usuario.
     * @see por el momento se dejara por si en dado caso lanza conflictos
     * @param getNameUser lo que hara es solamente traernos el dato de usuario ya que eso necesitamos
     * 
     */
    @Deprecated
    public void setNameUser(String NameUser){
        this.NameUser = NameUser;
    }
    public String getNameUser(){
        return NameUser;
    }
}