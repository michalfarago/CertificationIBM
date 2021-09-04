package ibm.java.academy.cerfiticationsapp.payload.request;

import java.util.Date;

import ibm.java.academy.cerfiticationsapp.model.Voucher.State;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoucherRequest {
    
	private State state;
	
	private String voucherCode;
	
	private Date validUntil;

    private Long userId;

    private Long certificationId;

}
