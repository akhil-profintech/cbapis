import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAnchorTrader } from '../anchor-trader.model';
import { AnchorTraderService } from '../service/anchor-trader.service';

@Component({
  standalone: true,
  templateUrl: './anchor-trader-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AnchorTraderDeleteDialogComponent {
  anchorTrader?: IAnchorTrader;

  constructor(
    protected anchorTraderService: AnchorTraderService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.anchorTraderService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
