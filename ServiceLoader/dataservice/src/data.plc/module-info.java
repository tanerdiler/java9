module data.plc {
    requires data.service;
    provides com.data.service.DataService
        with com.data.provider.PLCDataProvider;
}
