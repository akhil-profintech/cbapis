import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IRequestOffer } from '../request-offer.model';
import { RequestOfferService } from '../service/request-offer.service';

@Component({
  standalone: true,
  templateUrl: './request-offer-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class RequestOfferDeleteDialogComponent {
  requestOffer?: IRequestOffer;

  constructor(
    protected requestOfferService: RequestOfferService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.requestOfferService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
