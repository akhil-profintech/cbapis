import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ICollectionTransactionDetails } from '../collection-transaction-details.model';
import { CollectionTransactionDetailsService } from '../service/collection-transaction-details.service';

@Component({
  standalone: true,
  templateUrl: './collection-transaction-details-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CollectionTransactionDetailsDeleteDialogComponent {
  collectionTransactionDetails?: ICollectionTransactionDetails;

  constructor(
    protected collectionTransactionDetailsService: CollectionTransactionDetailsService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.collectionTransactionDetailsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
