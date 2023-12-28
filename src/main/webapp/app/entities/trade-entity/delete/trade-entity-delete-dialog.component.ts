import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ITradeEntity } from '../trade-entity.model';
import { TradeEntityService } from '../service/trade-entity.service';

@Component({
  standalone: true,
  templateUrl: './trade-entity-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class TradeEntityDeleteDialogComponent {
  tradeEntity?: ITradeEntity;

  constructor(
    protected tradeEntityService: TradeEntityService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tradeEntityService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
