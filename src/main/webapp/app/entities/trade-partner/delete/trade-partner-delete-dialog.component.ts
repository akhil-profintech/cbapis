import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ITradePartner } from '../trade-partner.model';
import { TradePartnerService } from '../service/trade-partner.service';

@Component({
  standalone: true,
  templateUrl: './trade-partner-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class TradePartnerDeleteDialogComponent {
  tradePartner?: ITradePartner;

  constructor(
    protected tradePartnerService: TradePartnerService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tradePartnerService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
