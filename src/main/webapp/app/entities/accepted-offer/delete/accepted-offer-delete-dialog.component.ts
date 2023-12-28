import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAcceptedOffer } from '../accepted-offer.model';
import { AcceptedOfferService } from '../service/accepted-offer.service';

@Component({
  standalone: true,
  templateUrl: './accepted-offer-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AcceptedOfferDeleteDialogComponent {
  acceptedOffer?: IAcceptedOffer;

  constructor(
    protected acceptedOfferService: AcceptedOfferService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.acceptedOfferService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
