import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IIndividualAssessment } from '../individual-assessment.model';
import { IndividualAssessmentService } from '../service/individual-assessment.service';

@Component({
  standalone: true,
  templateUrl: './individual-assessment-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class IndividualAssessmentDeleteDialogComponent {
  individualAssessment?: IIndividualAssessment;

  constructor(
    protected individualAssessmentService: IndividualAssessmentService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.individualAssessmentService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
