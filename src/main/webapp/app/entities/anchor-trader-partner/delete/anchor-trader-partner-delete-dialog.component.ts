import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAnchorTraderPartner } from '../anchor-trader-partner.model';
import { AnchorTraderPartnerService } from '../service/anchor-trader-partner.service';

@Component({
  standalone: true,
  templateUrl: './anchor-trader-partner-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AnchorTraderPartnerDeleteDialogComponent {
  anchorTraderPartner?: IAnchorTraderPartner;

  constructor(
    protected anchorTraderPartnerService: AnchorTraderPartnerService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.anchorTraderPartnerService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
