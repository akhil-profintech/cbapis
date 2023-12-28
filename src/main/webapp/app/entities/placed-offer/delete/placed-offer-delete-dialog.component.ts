import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IPlacedOffer } from '../placed-offer.model';
import { PlacedOfferService } from '../service/placed-offer.service';

@Component({
  standalone: true,
  templateUrl: './placed-offer-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class PlacedOfferDeleteDialogComponent {
  placedOffer?: IPlacedOffer;

  constructor(
    protected placedOfferService: PlacedOfferService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.placedOfferService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
