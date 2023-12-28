import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ICBCREProcess } from '../cbcre-process.model';

@Component({
  standalone: true,
  selector: 'jhi-cbcre-process-detail',
  templateUrl: './cbcre-process-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class CBCREProcessDetailComponent {
  @Input() cBCREProcess: ICBCREProcess | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
