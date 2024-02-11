import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ITradeChannel } from '../trade-channel.model';
import { TradeChannelService } from '../service/trade-channel.service';

@Component({
  standalone: true,
  templateUrl: './trade-channel-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class TradeChannelDeleteDialogComponent {
  tradeChannel?: ITradeChannel;

  constructor(
    protected tradeChannelService: TradeChannelService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tradeChannelService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
