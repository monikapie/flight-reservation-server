package rsi.pie.project.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface CityWebService {

    @WebMethod List<String> findAll();
}
